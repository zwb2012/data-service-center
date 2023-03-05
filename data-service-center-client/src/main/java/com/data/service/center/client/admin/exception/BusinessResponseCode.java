package com.data.service.center.client.admin.exception;


/**
 * Title: ExtensionResponseCode <br/>
 * Description: 业务自定义响应码样例  <br/>
 *
 * @author wenbo.zhuang
 */
public class BusinessResponseCode extends DefaultResponseCode {

    private static final long serialVersionUID = 2410173734769940865L;

    /**
     * 数据服务中心通用错误码
     */
    public static final String DATA_SERVICE_CENTER = SELF_FRAMEWORK_ERROR_CODE_PREFIX + "301";

    public BusinessResponseCode(String code, String msg, String userMsg) {
        super(code, msg, userMsg);
    }
}
