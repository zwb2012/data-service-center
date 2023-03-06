package com.data.service.center.services.admin.service.impl;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import com.data.service.center.client.general.enums.SqlAndSourceStatusEnum;
import com.data.service.center.dao.admin.mapper.DataSourceManageMapper;
import com.data.service.center.services.admin.service.DataSourceManageService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public List<DataSourceConfigDO> getEfficientDataSources() {
        Example example = new Example(DataSourceConfigDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", SqlAndSourceStatusEnum.EFFECTIVE.getStatus());
        return dataSourceManageMapper.selectAll();
    }

    @Override
    public void addDataSource(DataSourceConfigDO dataSourceConfigDO) {
        dataSourceManageMapper.insertSelective(dataSourceConfigDO);
    }
}
