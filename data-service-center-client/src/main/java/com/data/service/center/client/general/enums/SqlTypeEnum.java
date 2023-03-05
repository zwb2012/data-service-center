package com.data.service.center.client.general.enums;

import com.data.service.center.client.admin.exception.BusinessException;
import com.data.service.center.client.admin.exception.DefaultResponseCode;

import org.apache.commons.lang3.StringUtils;

/**
 * sql类型枚举
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/3 14:16
 **/
public enum SqlTypeEnum {

    SELECT, UPDATE, INSERT, DELETE;

    public static SqlTypeEnum determineSqlType(String sqlContent) {
        for (SqlTypeEnum sqlType : SqlTypeEnum.values()) {
            if (StringUtils.startsWithIgnoreCase(sqlContent, sqlType.name())) {
                return sqlType;
            }
        }
        throw new BusinessException(DefaultResponseCode.INVALID_ARGUMENT);
    }
}
