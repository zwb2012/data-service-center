package com.data.service.center.services.admin.datasource;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.data.service.center.client.admin.entity.SqlConfigDO;
import com.data.service.center.client.admin.exception.BusinessException;
import com.data.service.center.client.admin.exception.BusinessResponseCode;
import com.data.service.center.client.admin.exception.DefaultResponseCode;
import com.data.service.center.client.general.enums.SqlTypeEnum;
import com.data.service.center.services.admin.config.DynamicConfiguration;
import com.data.service.center.services.admin.service.SqlManageService;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

/**
 * 动态sql更新
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/6 13:41
 **/
@Slf4j
@Component
public class DynamicSqlContext {

    @Resource
    @Qualifier("dynamicDataSourceConfig")
    private DynamicConfiguration dynamicConfiguration;
    @Resource
    private SqlManageService sqlManageService;

    private final static Map<String, String> SQL_TYPE_MAP = new HashMap<>();

    private final BlockingQueue<SqlConfigDO> tasks = new LinkedBlockingQueue<>(1024);

    protected ScheduledExecutorService sqlEventExecutor;

    public static final String NAME_SPACE = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
        + "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >"
        + "<mapper namespace=\"{nameSpace}\"> {DO_SQL} </mapper>";

    static {
        SQL_TYPE_MAP.put(SqlTypeEnum.DELETE.name(), "<delete id=\"{sqlId}\">{sql_content}</delete>");
        SQL_TYPE_MAP.put(SqlTypeEnum.INSERT.name(), "<insert id=\"{sqlId}\">{sql_content}</insert>");
        SQL_TYPE_MAP.put(SqlTypeEnum.UPDATE.name(), "<update id=\"{sqlId}\">{sql_content}</update>");
        SQL_TYPE_MAP.put(SqlTypeEnum.SELECT.name(), "<select id=\"{sqlId}\">{sql_content}</select>");
    }

    public DynamicSqlContext() {
        sqlEventExecutor = new ScheduledThreadPoolExecutor(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("com.data.service.center.sql.timer");
            return t;
        });
    }

    @PostConstruct
    public void init() {
        // 解析已经保存的sql
        List<SqlConfigDO> efficientSqlList = sqlManageService.getEfficientSql();
        for (SqlConfigDO sqlConfigDO : efficientSqlList) {
            refreshLoadSql(sqlConfigDO);
        }

        // 启动守护线程执行sql变更操作
        sqlEventExecutor.submit(() -> {
            while (!sqlEventExecutor.isTerminated() && !sqlEventExecutor.isShutdown()) {
                try {
                    SqlConfigDO sqlConfig = tasks.take();
                    log.info("begin refreshLoadMst {}", sqlConfig);
                    if (StringUtils.isAnyBlank(sqlConfig.getSqlId(), sqlConfig.getSqlContent(),
                        sqlConfig.getNameSpace(), SQL_TYPE_MAP.get(sqlConfig.getSqlType()))) {
                        log.warn("sqlConfig is empty");
                        continue;
                    }
                    refreshLoadSql(sqlConfig);
                } catch (Exception e) {
                    log.error("load data failed.", e);
                }
            }
        });
    }

    private void refreshLoadSql(SqlConfigDO sqlConfig) {
        try {
            String sqlType = sqlConfig.getSqlType();
            String nameSpace = sqlConfig.getNameSpace();
            String sqlId = sqlConfig.getSqlId();
            String sqlContent = sqlConfig.getSqlContent();

            String doSql = SQL_TYPE_MAP.get(sqlType).replace("{sqlId}", sqlId).replace("{sqlContent}", sqlContent);
            String finalSql = NAME_SPACE.replace("{nameSpace}", nameSpace).replace("{DO_SQL}", doSql);
            String sourceSqlId = nameSpace + "." + sqlId;

            ByteArrayResource resource = new ByteArrayResource(finalSql.getBytes(StandardCharsets.UTF_8));

            dynamicConfiguration.removeLoadResources(sourceSqlId);

            // 解析xml-sql ---> 解析完成后会执行 @this.addMappedStatement
            XMLMapperBuilder xmlMapperBuilder =
                new XMLMapperBuilder(resource.getInputStream(), dynamicConfiguration, sourceSqlId, dynamicConfiguration.getSqlFragments());
            xmlMapperBuilder.parse();
        } catch (Exception e) {
            throw new BusinessException(BusinessResponseCode.SQL_PARSER_ERROR);
        }
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
}
