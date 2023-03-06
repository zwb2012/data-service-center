package com.data.service.center.client.admin.exception;


/**
 * Title: ExtensionResponseCode <br/>
 * Description: 业务自定义响应码样例 <br/>
 *
 * @author wenbo.zhuang
 */
public class BusinessResponseCode extends DefaultResponseCode {

    private static final long serialVersionUID = 2410173734769940865L;

    /**
     * 数据服务中心通用错误码
     */
    public static final String DATA_SERVICE_CENTER = SELF_FRAMEWORK_ERROR_CODE_PREFIX + "301";

    public static final BusinessResponseCode SQL_PARSER_ERROR =
        new BusinessResponseCode(DATA_SERVICE_CENTER + "00001", "sql parse error!", "sql解析失败");

    public static final BusinessResponseCode DATA_SOURCE_CONFIG_ERROR =
        new BusinessResponseCode(DATA_SERVICE_CENTER + "000012", "data source config error!", "数据源配置失败");


    public BusinessResponseCode(String code, String msg, String userMsg) {
        super(code, msg, userMsg);
    }
}
