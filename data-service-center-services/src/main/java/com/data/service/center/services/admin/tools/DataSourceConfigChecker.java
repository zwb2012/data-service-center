package com.data.service.center.services.admin.tools;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import com.data.service.center.client.admin.exception.BusinessException;
import com.data.service.center.client.admin.exception.DefaultResponseCode;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据源配置检查工具类
 *
 * @author wenbo.zhuang
 * @date 2023/03/06:21:34
 */
@NoArgsConstructor
public class DataSourceConfigChecker {

    public static void checkerDataSourceConfig(DataSourceConfigDO sourceConfigDO) throws BusinessException {
        if (sourceConfigDO == null || StringUtils.isAnyEmpty(sourceConfigDO.getUrl(), sourceConfigDO.getDbName(),
                sourceConfigDO.getNameSpace(), sourceConfigDO.getPassword(), sourceConfigDO.getUserName())) {
            throw new BusinessException(DefaultResponseCode.INVALID_ARGUMENT);
        }
        if (StringUtils.isEmpty(sourceConfigDO.getDataSourceType())) {
            sourceConfigDO.setDataSourceType("com.alibaba.druid.pool.DruidDataSource");
        }
    }
}
