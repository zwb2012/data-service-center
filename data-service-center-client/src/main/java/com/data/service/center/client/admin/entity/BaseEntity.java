package com.data.service.center.client.admin.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据库基础类
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/2 11:31
 **/
@Setter
@Getter
@ToString
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 777007238610362121L;

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
