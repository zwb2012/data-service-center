package com.data.service.center.client.admin.exception;


import com.data.service.center.client.admin.constant.NumberConstant;
import com.data.service.center.client.admin.constant.SelfStringConstant;

/**
 * Title: AbstractGeneralCode <br/>
 * Description: AbstractGeneralCode <br/>
 *
 * @author wenbo.zhuang
 * @date 2023/03/03:0:19
 */
public abstract class AbstractGeneralCode implements GeneralCode {

    private static final long serialVersionUID = 414287196738369865L;


    @Override
    public int getHttpStatus() {
        String code = getCode();
        if (null != code && code.length() == NumberConstant.FOUR && code.startsWith(SelfStringConstant.ZERO_STR)) {
            return Integer.parseInt(code);
        } else {
            return 500;
        }
    }
}
