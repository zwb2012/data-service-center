package com.data.sc.platform.service.impl;

import com.data.sc.platform.mapper.DataSourceManageMapper;
import com.data.sc.platform.model.DataSourceConfig;
import com.data.sc.platform.service.DataSourceManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenbo.zhuang
 * @date 2022/11/05:20:21
 */
@Service
public class DataSourceManageServiceImpl implements DataSourceManageService {

    @Resource
    private DataSourceManageMapper dataSourceManageMapper;

    @Override
    public List<DataSourceConfig> getAllDataSources() {
        return dataSourceManageMapper.selectAllDataSources();
    }
}
