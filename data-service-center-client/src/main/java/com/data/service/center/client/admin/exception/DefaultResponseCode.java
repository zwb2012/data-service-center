package com.data.service.center.client.admin.exception;

import lombok.Builder;

/**
 * Title: DefaultResponseCode <br/>
 * Description: 默认响应码实现  <br/>
 *
 * @author wenbo.zhuang
 * @date 2023/03/03:0:19
 */
@Builder
public class DefaultResponseCode extends AbstractGeneralCode {
    private static final long serialVersionUID = -3247440889009160154L;

    public static final GeneralCode SUCCESS = new DefaultResponseCode("0", "Success", "成功");
    public static final GeneralCode INVALID_ARGUMENT = new DefaultResponseCode("0400", "Invalid Argument", "无效的参数");
    public static final GeneralCode UN_AUTH = new DefaultResponseCode("0401", "Unauthenticated", "未认证的请求或身份已过期");
    public static final GeneralCode UN_PERMISSION = new DefaultResponseCode("0403", "Permission Denied", "无权限");
    public static final GeneralCode NOT_FOUND = new DefaultResponseCode("0404", "Not Found", "资源找不到");
    public static final GeneralCode METHOD_NOT_ALLOWED = new DefaultResponseCode("0405", "Method Not Allowed", "不支持的方法");
    public static final GeneralCode ALREADY_EXISTS = new DefaultResponseCode("0409", "Already Exists", "资源已存在");
    public static final GeneralCode TOO_LARGE = new DefaultResponseCode("0414", "Request Body Too Large", "请求体太大");
    public static final GeneralCode PARAMETER_OUT_OF_RANGE = new DefaultResponseCode("0415", "Parameter Out Of Range", "请求参数超出范围");
    public static final GeneralCode RESOURCE_EXHAUSTED = new DefaultResponseCode("0429", "Resource Exhausted", "超出配额，或者超过限流");
    public static final GeneralCode SERVER_ERROR = new DefaultResponseCode("0500", "Internal Server Error", "服务器内部错误");
    public static final GeneralCode NOT_IMPLEMENTED = new DefaultResponseCode("0501", "Resource Exhausted", "API未实现");
    public static final GeneralCode BAD_GATEWAY = new DefaultResponseCode("0502", "Bad Gateway", "Bad Gateway");
    public static final GeneralCode UNAVAILABLE = new DefaultResponseCode("0503", "Service Unavailable", "API服务不可用");
    public static final GeneralCode TIMEOUT = new DefaultResponseCode("0504", "Service Timeout", "调用超时");
    public static final GeneralCode REMOTE_HTTP_ERROR = new DefaultResponseCode("0505", "Remote Http Error", "远程http请求失败");
    public static final GeneralCode CONFIG_ERROR = new DefaultResponseCode("0506", "service config error", "服务配置错误");


    public static final GeneralCode UN_KNOWN = new DefaultResponseCode(COMMON_ERROR_CODE_PREFIX + "0001", "Unknown", "Unknown");
    public static final GeneralCode DATA_DUPLICATE = new DefaultResponseCode(COMMON_ERROR_CODE_PREFIX + "0002", "Duplicate entry key", "数据重复");

    private String code;
    private String msg;
    private String errMsg;

    public DefaultResponseCode(String code, String msg, String errMsg) {
        this.code = code;
        this.msg = msg;
        this.errMsg = errMsg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
