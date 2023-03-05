package com.data.service.center.client.admin.exception;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Title: GeneralCode <br/>
 * Description: 响应吗Code 接口  <br/>
 *
 * @author wenbo.zhuang
 * @date 2023/03/03:0:16
 */
public interface GeneralCode extends Serializable {
    /**
     * 异常编码定义：共15位
     * 错误码 :
     * aaaaa  bbb ccc dddd
     * 5位   3位  3位  4位
     * a:产品编号,默认产品id从1-100，普通产品从101开始,产品ID编号不足5位前面补0
     * 00000-09999 供内部产品使用,
     * 10000-99999 外部产品使用
     * b:系统编号，
     * c:子系统编号
     * d:错误码编号，0000-0999作为通用错误码预留，业务自定义从1000开始
     */
    String SELF_FRAMEWORK_ERROR_CODE_PREFIX = "00150512";

    /**
     * 通用错误码前缀
     */
    String COMMON_ERROR_CODE_PREFIX = SELF_FRAMEWORK_ERROR_CODE_PREFIX + "000";

    /**
     * 返回code
     *
     * @return code
     */
    String getCode();

    /**
     * 返回msg
     *
     * @return msg
     */
    String getMsg();

    /**
     * 返回userMsg
     *
     * @return userMsg
     */
    String getErrMsg();

    /**
     * 返回HttpStatus
     *
     * @return HttpStatus
     */
    @JsonIgnore
    @JSONField(serialize = false)
    int getHttpStatus();
}
