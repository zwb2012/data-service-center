package com.data.service.center.services.admin.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import com.data.service.center.dao.admin.mapper.DataSourceManageMapper;
import com.data.service.center.services.admin.datasource.DynamicDataSource;
import com.data.service.center.services.admin.service.DataSourceManageService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenbo.zhuang
 * @date 2022/11/05:20:21
 */
@Service
public class DataSourceManageServiceImpl implements DataSourceManageService {
    @Resource
    private DataSourceManageMapper dataSourceManageMapper;

    @Override
    public List<DataSourceConfigDO> getAllDataSources() {
        return dataSourceManageMapper.selectAll();
    }
}
