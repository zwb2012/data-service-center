package com.data.service.center.services.admin.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义数据源
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/3 15:56
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource() {}

    protected Object determineCurrentLookupKey() {
        return "platform";
    }
}
