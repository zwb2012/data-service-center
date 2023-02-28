package com.data.sc.platform.mapper;

import com.data.sc.platform.model.DataSourceConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wenbo.zhuang
 * @date 2022/11/05:20:09
 */
@Mapper
public interface DataSourceManageMapper {

    List<DataSourceConfig> selectAllDataSources();

}
