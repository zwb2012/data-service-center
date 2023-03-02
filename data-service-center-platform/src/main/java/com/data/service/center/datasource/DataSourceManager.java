package com.data.service.center.datasource;

import com.data.service.center.services.admin.service.DataSourceManageService;
import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * 数据源管理类
 *
 * @author wenbo.zhuang
 * @date 2022/11/05:19:25
 */
@Component
public class DataSourceManager implements ApplicationContextAware {

    @Resource
    private DataSourceManageService dataSourceManageService;

    @PostConstruct
    public void init() {
//        List<DataSourceConfig> allDataSources = dataSourceManageService.getAllDataSources();
//        for (DataSourceConfig dataSource : allDataSources) {
//
//            DruidDataSource druidDataSource = new DruidDataSource();
//            druidDataSource.setUsername(dataSource.getUserName());
//            druidDataSource.setPassword(dataSource.getPassword());
//            druidDataSource.setUrl(dataSource.getUrl());
//            druidDataSource.init();
//
//            System.out.println(JSON.toJSONString(dataSource));
//
//
//
//        }
    }

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public void dataSource(DataSourceConfigDO dataSourceConfig) {
    }
}