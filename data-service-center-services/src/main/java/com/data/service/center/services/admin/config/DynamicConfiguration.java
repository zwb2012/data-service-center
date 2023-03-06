package com.data.service.center.services.admin.config;


import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * 动态mybatis配置
 *
 * @author wenbo.zhuang
 * @date 2022/11/18:0:41
 */
@Slf4j
@Component
@Qualifier("dynamicDataSourceConfig")
public class DynamicConfiguration extends Configuration {

    /**
     * sqlMapper<br/>
     * key=nameSpace+sqlId, value=执行的sql
     */
    protected Map<String, MappedStatement> dynamicMappedStatements = new StrictMap<MappedStatement>("Dynamic Mapped Statements collection")
        .conflictMessageProducer((savedValue, targetValue) -> ". please check " + savedValue.getResource() + " and " + targetValue.getResource());

    private final AtomicBoolean inited = new AtomicBoolean(false);

    @EventListener(ApplicationReadyEvent.class)
    public void inited() {
        this.inited.set(true);
    }

    @Override
    public boolean hasStatement(String statementName, boolean validateIncompleteStatements) {
        if (validateIncompleteStatements) {
            buildAllStatements();
        }
        return dynamicMappedStatements.containsKey(statementName);
    }

    @Override
    public MappedStatement getMappedStatement(String id, boolean validateIncompleteStatements) {
        if (validateIncompleteStatements) {
            buildAllStatements();
        }
        return dynamicMappedStatements.get(id);
    }

    /**
     * 服务启动阶段会执行该方法, 解析mapper.xml进行sql解析。初始化阶段直接放到map
     */
    @Override
    public void addMappedStatement(MappedStatement ms) {
        if (!inited.get()) {
            this.dynamicMappedStatements.put(ms.getId(), ms);
        } else {
            Map<String, MappedStatement> mappedStatements =
                new StrictMap<>("Temp Mapped Statements collection", this.dynamicMappedStatements).conflictMessageProducer(
                    (savedValue, targetValue) -> ". please check " + savedValue.getResource() + " and " + targetValue.getResource());
            mappedStatements.remove(ms.getId());
            mappedStatements.put(ms.getId(), ms);
            this.dynamicMappedStatements = mappedStatements;
        }
    }

    public void removeLoadResources(String sqlId) {
        this.loadedResources.remove(sqlId);
    }
}
