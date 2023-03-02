package com.data.service.center.services.admin.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 动态mybatis配置
 *
 * @author wenbo.zhuang
 * @date 2022/11/18:0:41
 */
@Slf4j
@Component
public class DynamicConfiguration extends Configuration {
    protected Map<String, MappedStatement> dynamicMappedStatements = new StrictMap<MappedStatement>("Dynamic Mapped Statements collection").conflictMessageProducer((savedValue, targetValue) -> ". please check " + savedValue.getResource() + " and " + targetValue.getResource());

    private final AtomicBoolean refresh = new AtomicBoolean(false);

    private final BlockingQueue<String> tasks = new LinkedBlockingQueue<String>(1024);

    public void removeLoadResources(String sqlId) {
        this.loadedResources.remove(sqlId);
    }

    public static final String NAME_SPACE = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >" +
            "<mapper namespace=\"{nameSpace}\"> {DO_SQL} </mapper>";

    public void addTask(String sqlId) {
        this.tasks.add(sqlId);
    }

    @Override
    public MappedStatement getMappedStatement(String id, boolean validateIncompleteStatements) {
        if (validateIncompleteStatements) {
            buildAllStatements();
        }
        return dynamicMappedStatements.get(id);
    }

    @Override
    public MappedStatement getMappedStatement(String id) {
        return this.getMappedStatement(id, true);
    }

    @Override
    public void addMappedStatement(MappedStatement ms) {
        if (!refresh.get()) {
            this.dynamicMappedStatements.put(ms.getId(), ms);
        } else {
            Map<String, MappedStatement> mappedStatements = new StrictMap<>("Temp Mapped Statements collection", this.dynamicMappedStatements).conflictMessageProducer((savedValue, targetValue) -> ". please check " + savedValue.getResource() + " and " + targetValue.getResource());
            mappedStatements.remove(ms.getId());
            mappedStatements.put(ms.getId(), ms);
            this.dynamicMappedStatements = mappedStatements;
        }
    }

    private void refreshLoadMst(String sqlContent) throws Exception {
        if (!refresh.get()) {
            refresh.set(true);
        }

        ByteArrayResource resource = new ByteArrayResource(sqlContent.getBytes(StandardCharsets.UTF_8));

        this.removeLoadResources("ust.common.selectById");

        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(), this, "ust.common.selectById", this.getSqlFragments());
        xmlMapperBuilder.parse();
    }



    @PostConstruct
    public void init() {
        new Thread(() -> {
            try {
                while (true) {
                    String sqlId = tasks.take();
                    log.info("begin refreshLoadMst {}", sqlId);
                    refreshLoadMst(NAME_SPACE);
                }
            } catch (Exception e) {
                log.error("load data failed.", e);
            }
        }).start();
    }
}
