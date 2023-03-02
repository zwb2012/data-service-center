package com.data.service.center.client.admin.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * sql配置请求参数
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:23:39
 */
@Getter
@Setter
@ToString
public class SqlRequest implements Serializable {
    private static final long serialVersionUID = 7943352619021870680L;

    /**
     * sql命名空间
     */
    private String nameSpace;

    /**
     * sql表示ID
     */
    private String sqlId;

    /**
     * sql配置内容
     */
    private String sqlContent;
}
