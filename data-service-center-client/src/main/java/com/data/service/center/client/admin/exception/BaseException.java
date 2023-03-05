package com.data.service.center.client.admin.exception;


import java.text.MessageFormat;

/**
 * Title: BaseException <br/>
 *
 * @author wenbo.zhuang
 * @date 2023/03/03:0:15
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -4567087136091231834L;

    private String code;
    private String message;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(GeneralCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
    }

    public BaseException(GeneralCode responseCode, String extMsg) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
        this.message = MessageFormat.format("{0} -> {1}", responseCode.getMsg(), extMsg);
    }

    public String getCode() {
        return code;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
