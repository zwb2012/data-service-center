package com.data.service.center.services.admin.tools;

import cn.hutool.core.lang.Assert;
import com.data.service.center.client.admin.entity.SqlConfigDO;
import com.data.service.center.client.admin.exception.BusinessException;
import com.data.service.center.client.admin.exception.DefaultResponseCode;
import com.data.service.center.client.general.enums.SqlTypeEnum;

import org.apache.commons.lang3.StringUtils;

/**
 * sql配置校验
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/3 15:01
 **/
public class SqlConfigChecker {

    public static void checkerSqlConfig(SqlConfigDO sqlConfigDO) throws BusinessException {
        if (sqlConfigDO == null || StringUtils.isAnyEmpty(sqlConfigDO.getSqlId(), sqlConfigDO.getSqlContent(),
            sqlConfigDO.getNameSpace())) {
            throw new BusinessException(DefaultResponseCode.INVALID_ARGUMENT);
        }

        SqlTypeEnum sqlType = SqlTypeEnum.determineSqlType(sqlConfigDO.getSqlContent());
        sqlConfigDO.setSqlType(sqlType.name());
    }
}
