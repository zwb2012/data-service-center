package com.data.service.center.client.admin.entity;

import com.data.service.center.client.general.enums.SqlAndSourceStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * sql实体bean
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:22:40
 */
@Getter
@Setter
@ToString
@Table(name = "data_service_center_sql")
public class SqlDO extends BaseEntity {

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
     * sql内容
     */
    private String sqlContent;

    /**
     * 状态
     * @see com.data.service.center.client.general.enums.SqlAndSourceStatusEnum
     */
    private Integer status;
}
