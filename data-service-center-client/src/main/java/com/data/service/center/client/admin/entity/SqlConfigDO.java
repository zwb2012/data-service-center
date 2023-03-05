package com.data.service.center.client.admin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * sql实体bean
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:22:40
 */
@Getter
@Setter
@ToString
@Table(name = "dsc_sql_config")
public class SqlConfigDO extends BaseEntity {

    private static final long serialVersionUID = -5993069876066168270L;

    /**
     * sql所属namespace
     */
    private String nameSpace;

    /**
     * sql表示ID
     */
    private String sqlId;

    /**
     * sql类型 select, update, delete, insert
     */
    private String sqlType;

    /**
     * sql内容
     */
    private String sqlContent;

    /**
     * 状态
     * @see com.data.service.center.client.general.enums.SqlAndSourceStatusEnum
     */
    private Integer status;
}
