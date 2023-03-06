package com.data.service.center.client.admin.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 数据源配置请求参数
 *
 * @author wenbo.zhuang
 * @date 2023/03/06:21:28
 */
@Getter
@Setter
@ToString
public class DataSourceConfigRequest implements Serializable {

    private static final long serialVersionUID = 550345497255145349L;

    /**
     * 命名空间
     */
    @NotNull(message = "命名空间不能为空")
    private String nameSpace;

    /**
     * 数据库名
     */
    @NotNull(message = "数据源不能为空")
    private String dbName;

    /**
     * 数据源类型 druid等
     */
    private String dataSourceType;

    /**
     * 数据库url
     */
    @NotNull(message = "数据源url为空")
    private String url;

    /**
     * 数据库用户名
     */
    @NotNull(message = "数据源用户名不能为空")
    private String userName;

    /**
     * 数据库密码
     */
    @NotNull(message = "数据源密码不能为空")
    private String password;
}
