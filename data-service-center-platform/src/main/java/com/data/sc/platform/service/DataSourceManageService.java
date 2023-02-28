package com.data.sc.platform.service;

import com.data.sc.platform.model.DataSourceConfig;

import java.util.List;

/**
 * @author wenbo.zhuang
 * @date 2022/11/05:20:08
 */
public interface DataSourceManageService {

    List<DataSourceConfig> getAllDataSources();
}
