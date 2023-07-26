package com.data.service.center.client.admin.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.data.service.center.client.admin.enums.SqlAndSourceStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 配置的数据源
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/2 11:31
 **/
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "dsc_data_source_config")
public class DataSourceConfigDO extends BaseEntity {

    private static final long serialVersionUID = -334536119129801448L;

    /**
     * 命名空间
     */
    private String nameSpace;

    /**
     * 数据库名
     */
    private String dbName;

    /**
     * 数据源类型 druid等
     */
    private String dataSourceType;

    /**
     * 数据库url
     */
    @Column(name = "db_url")
    private String url;

    /**
     * 数据库用户名
     */
    @Column(name = "db_user_name")
    private String userName;

    /**
     * 数据库密码
     */
    @Column(name = "db_password")
    private String password;

    /**
     * 状态
     * 
     * @see SqlAndSourceStatusEnum
     */
    private Integer status;
}
