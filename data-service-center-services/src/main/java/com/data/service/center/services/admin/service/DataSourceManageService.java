package com.data.service.center.services.admin.service;

import java.util.List;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;

/**
 * @author wenbo.zhuang
 * @date 2022/11/05:20:08
 */
public interface DataSourceManageService {

    /**
     * 查询全部数据源配置
     * 
     * @return 数据源列表
     */
    List<DataSourceConfigDO> getAllDataSources();
}
