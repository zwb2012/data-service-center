package com.data.service.center.services.admin.config;


import com.data.service.center.client.admin.entity.SqlConfigDO;
import com.data.service.center.client.admin.exception.BusinessException;
import com.data.service.center.client.admin.exception.DefaultResponseCode;
import com.data.service.center.client.general.enums.SqlTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
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

    /**
     * sqlMapper<br/>
     * key=nameSpace+sqlId, value=执行的sql
     */
    protected Map<String, MappedStatement> dynamicMappedStatements = new StrictMap<MappedStatement>("Dynamic Mapped Statements collection")
        .conflictMessageProducer((savedValue, targetValue) -> ". please check " + savedValue.getResource() + " and " + targetValue.getResource());

    private final AtomicBoolean init = new AtomicBoolean(false);

    private final BlockingQueue<SqlConfigDO> tasks = new LinkedBlockingQueue<>(1024);

    private final static Map<String, String> SQL_TYPE_MAP = new HashMap<>();

    static {
        SQL_TYPE_MAP.put(SqlTypeEnum.DELETE.name(), "<delete id=\"{sqlId}\">{sql_content}</delete>");
        SQL_TYPE_MAP.put(SqlTypeEnum.INSERT.name(), "<insert id=\"{sqlId}\">{sql_content}</insert>");
        SQL_TYPE_MAP.put(SqlTypeEnum.UPDATE.name(), "<update id=\"{sqlId}\">{sql_content}</update>");
        SQL_TYPE_MAP.put(SqlTypeEnum.SELECT.name(), "<select id=\"{sqlId}\">{sql_content}</select>");
    }


    public static final String NAME_SPACE = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
        + "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >"
        + "<mapper namespace=\"{nameSpace}\"> {DO_SQL} </mapper>";

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
        if (!init.get()) {
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

    private void refreshLoadSql(SqlConfigDO sqlConfig) throws Exception {
        if (!init.get()) {
            init.set(true);
        }
        String sqlType = sqlConfig.getSqlType();
        String nameSpace = sqlConfig.getNameSpace();
        String sqlId = sqlConfig.getSqlId();
        String sqlContent = sqlConfig.getSqlContent();

        String doSql = SQL_TYPE_MAP.get(sqlType).replace("{sqlId}", sqlId).replace("{sqlContent}", sqlContent);
        String finalSql = NAME_SPACE.replace("{nameSpace}", nameSpace).replace("{DO_SQL}", doSql);
        String sourceSqlId = nameSpace + "." + sqlId;

        ByteArrayResource resource = new ByteArrayResource(finalSql.getBytes(StandardCharsets.UTF_8));

        this.removeLoadResources(sourceSqlId);

        // 解析xml-sql ---> 解析完成后会执行 @this.addMappedStatement
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(), this, sourceSqlId, this.getSqlFragments());
        xmlMapperBuilder.parse();
    }


    public void removeLoadResources(String sqlId) {
        this.loadedResources.remove(sqlId);
    }

    public void addTask(SqlConfigDO sqlConfig) {
        if (sqlConfig == null
            || StringUtils.isAnyBlank(sqlConfig.getSqlId(), sqlConfig.getSqlContent(), sqlConfig.getNameSpace(),
                SQL_TYPE_MAP.get(sqlConfig.getSqlType()))) {
            log.warn("sqlConfig is illegal: {}", sqlConfig);
            throw new BusinessException(DefaultResponseCode.INVALID_ARGUMENT);
        }
        this.tasks.add(sqlConfig);
    }

    @PostConstruct
    public void init() {
        new Thread(() -> {
            try {
                while (true) {
                    SqlConfigDO sqlConfig = tasks.take();
                    log.info("begin refreshLoadMst {}", sqlConfig);
                    if (StringUtils.isAnyBlank(sqlConfig.getSqlId(), sqlConfig.getSqlContent(),
                        sqlConfig.getNameSpace(), SQL_TYPE_MAP.get(sqlConfig.getSqlType()))) {
                        log.warn("sqlConfig is empty");
                        continue;
                    }
                    refreshLoadSql(sqlConfig);
                }
            } catch (Exception e) {
                log.error("load data failed.", e);
            }
        }).start();
    }
}
