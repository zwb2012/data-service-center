package com.data.service.center.dao.admin.mapper;

import java.util.List;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataSourceManageMapper {

    /**
     * 查询全部数据源配置
     * 
     * @return 数据源列表
     */
    List<DataSourceConfigDO> selectAllDataSources();
}
