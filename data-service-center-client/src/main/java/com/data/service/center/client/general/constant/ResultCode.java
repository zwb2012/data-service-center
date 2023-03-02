package com.data.service.center.client.general.constant;

/**
 * description: 响应操作码
 *
 * @author wenbo.zhuang
 * @date 2022/02/17 17:11
 **/
public enum  ResultCode implements IResultCode {
    /**
     * 成功
     */
    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败"),
    VALIDATE_FAILED("404", "参数检验失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限");

    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
