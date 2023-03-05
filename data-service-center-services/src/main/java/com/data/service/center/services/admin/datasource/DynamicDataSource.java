package com.data.service.center.services.admin.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * 自定义数据源
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/3 15:56
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Nullable
    private Map<Object, DataSource> resolvedDataSources;

    public DynamicDataSource() {}


    @Nullable
    Map<Object, DataSource> getResolvedDataSources() {
        return resolvedDataSources;
    }

    protected Object determineCurrentLookupKey() {
        return "platform";
    }
}
