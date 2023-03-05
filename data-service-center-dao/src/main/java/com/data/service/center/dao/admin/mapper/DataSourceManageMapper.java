package com.data.service.center.dao.admin.mapper;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import com.data.service.center.dao.general.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源管理mapper
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:22:46
 */

@Mapper
public interface DataSourceManageMapper extends BaseMapper<DataSourceConfigDO> {
}
