package com.data.service.center.client.general.entity;

import com.data.service.center.client.general.constant.IResultCode;
import com.data.service.center.client.general.constant.ResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通用响应结果
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/2 19:26
 **/
@Getter
@Setter
@ToString
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 2509172013335512453L;

    /**
     * 状态码
     */
    private String code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据封装
     */
    private T data;

    protected BaseResult() {
    }

    protected BaseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> BaseResult<T> success() {
        return new BaseResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> BaseResult<T> success(T data, String message) {
        return new BaseResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> BaseResult<T> failed(IResultCode errorCode) {
        return new BaseResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> BaseResult<T> failed(IResultCode errorCode, String message) {
        return new BaseResult<>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param code    错误码
     * @param message 提示信息
     */
    public static <T> BaseResult<T> failed(String code, String message) {
        return new BaseResult<>(code, message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> BaseResult<T> failed(String message) {
        return new BaseResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> BaseResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> BaseResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> BaseResult<T> validateFailed(String message) {
        return new BaseResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 通用返回
     *
     * @param errorCode 返回码
     */
    public static <T> BaseResult<T> result(IResultCode errorCode) {
        return new BaseResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> BaseResult<T> unauthorized(T data) {
        return new BaseResult<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> BaseResult<T> forbidden(T data) {
        return new BaseResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

}
