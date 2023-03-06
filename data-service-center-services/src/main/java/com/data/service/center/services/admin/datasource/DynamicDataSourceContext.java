package com.data.service.center.services.admin.datasource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import com.data.service.center.client.admin.exception.BusinessException;
import com.data.service.center.client.admin.exception.BusinessResponseCode;
import com.data.service.center.services.admin.service.DataSourceManageService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

/**
 * 数据源操作
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/3 16:14
 **/
@Slf4j
@Component
public class DynamicDataSourceContext {

    @Resource
    private DynamicDataSource dynamicDataSource;
    @Resource
    private DataSourceManageService dataSourceManageService;

    private final BlockingQueue<DataSourceConfigDO> tasks = new LinkedBlockingQueue<>(1024);

    protected ScheduledExecutorService dataSourceEventExecutor;


    @PostConstruct
    public void init() {
        List<DataSourceConfigDO> allDataSources = dataSourceManageService.getEfficientDataSources();
        for (DataSourceConfigDO dataSourceConfig : allDataSources) {
            refreshLoadDataSource(dataSourceConfig);
        }

        // 启动守护线程执行sql变更操作
        dataSourceEventExecutor.submit(() -> {
            while (!dataSourceEventExecutor.isTerminated() && !dataSourceEventExecutor.isShutdown()) {
                try {
                    DataSourceConfigDO dataSourceConfigDO = tasks.take();
                    log.info("begin refreshLoadMst {}", dataSourceConfigDO);
                    refreshLoadDataSource(dataSourceConfigDO);
                } catch (Exception e) {
                    log.error("load data failed.", e);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void refreshLoadDataSource(DataSourceConfigDO dataSourceConfig) {
        try {
            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName(dataSourceConfig.getDataSourceType());

            Map<Object, DataSource> dataSourceMap = dynamicDataSource.getResolvedDataSources();
            DataSource oldDataSource = dataSourceMap.get(dataSourceConfig.getNameSpace());
            if (oldDataSource != null) {
                oldDataSource = DataSourceBuilder.create().type(dataSourceType).build();
                dataSourceMap.put(dataSourceConfig.getNameSpace(), oldDataSource);
            } else {
                DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
                dataSourceMap.put(dataSourceConfig.getNameSpace(), dataSource);
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessResponseCode.DATA_SOURCE_CONFIG_ERROR);
        }
    }

    public void addTask(DataSourceConfigDO dataSourceConfigDO) {
        this.tasks.add(dataSourceConfigDO);
    }
}
