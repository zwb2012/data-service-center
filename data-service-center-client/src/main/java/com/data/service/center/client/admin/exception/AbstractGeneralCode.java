package com.data.service.center.client.admin.exception;

/**
 * Title: AbstractGeneralCode <br/>
 * Description: AbstractGeneralCode <br/>
 *
 * @author wenbo.zhuang
 * @date 2023/03/03:0:19
 */
public abstract class AbstractGeneralCode implements GeneralCode {
    private static final long serialVersionUID = 4303658832392863325L;

    @Override
    public int getHttpStatus() {
        String code = getCode();
        if (null != code && code.length() == 4 && code.startsWith("0")) {
            return Integer.parseInt(code);
        } else {
            return 500;
        }
    }
}
