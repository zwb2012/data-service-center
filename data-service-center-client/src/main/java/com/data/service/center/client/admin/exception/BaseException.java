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

    private String errCode;

    private String errMsg;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String code, String message) {
        super(message);
        this.errCode = code;
        this.errMsg = message;
    }

    public BaseException(GeneralCode responseCode) {
        super(responseCode.getMsg());
        this.errCode = responseCode.getCode();
        this.errMsg = responseCode.getMsg();
    }

    public BaseException(GeneralCode responseCode, String errMsg) {
        super(responseCode.getErrMsg());
        this.errCode = responseCode.getCode();
        this.errMsg = MessageFormat.format("{0} -> {1}", responseCode.getMsg(), errMsg);
    }



    protected BaseException(Throwable cause, GeneralCode responseCode) {
        super(cause);
        this.errMsg = responseCode.getCode();
    }


    protected BaseException(String errMsg) {
        super(errMsg);
        errCode = DefaultResponseCode.SERVER_ERROR.getCode();
    }


    public String getErrCode() {
        return errCode;
    }

    @Override
    public String getMessage() {
        return errMsg;
    }

    public void setMessage(String message) {
        this.errMsg = message;
    }
}
