package com.data.sc.platform.datasource;

import cn.hutool.db.ds.DataSourceWrapper;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.data.sc.platform.model.DataSourceConfig;
import com.data.sc.platform.service.DataSourceManageService;
import com.sun.xml.internal.ws.util.CompletedFuture;
import lombok.SneakyThrows;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;


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
    public void init() throws SQLException {
        List<DataSourceConfig> allDataSources = dataSourceManageService.getAllDataSources();
        for (DataSourceConfig dataSource : allDataSources) {

            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setUsername(dataSource.getUserName());
            druidDataSource.setPassword(dataSource.getPassword());
            druidDataSource.setUrl(dataSource.getUrl());
            druidDataSource.init();

            System.out.println(JSON.toJSONString(dataSource));



        }
    }

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public void dataSource(DataSourceConfig dataSourceConfig) {
    }
}