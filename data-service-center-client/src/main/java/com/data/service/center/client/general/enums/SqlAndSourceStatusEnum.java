package com.data.service.center.client.general.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据源和sql状态枚举
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:23:22
 */
@Getter
@AllArgsConstructor
public enum SqlAndSourceStatusEnum {

    /**
     * 生效状态
     */
    EFFECTIVE(1, "生效状态"),

    NON_EFFECTIVE(0, "无效状态"),
    ;

    private final int status;

    private final String desc;
}