package com.data.service.center.client.admin.exception;

/**
 * 业务异常
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/3 12:00
 **/
public class BusinessException extends BaseException {

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(GeneralCode responseCode) {
        super(responseCode);
    }
}
