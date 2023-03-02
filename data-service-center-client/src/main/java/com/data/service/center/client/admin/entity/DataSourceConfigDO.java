package com.data.service.center.client.admin.entity;

import com.data.service.center.client.general.enums.SqlAndSourceStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * 配置的数据源
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/2 11:31
 **/
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "data_service_center_data_source_config")
public class DataSourceConfigDO extends BaseEntity {

    private static final long serialVersionUID = -334536119129801448L;

    /**
     * 数据库url
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String userName;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 状态
     * @see com.data.service.center.client.general.enums.SqlAndSourceStatusEnum
     */
    private Integer status;
}
