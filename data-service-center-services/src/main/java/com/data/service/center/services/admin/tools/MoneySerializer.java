package com.data.service.center.services.admin.tools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 格式化输出
 *
 * @author wenbo.zhuang
 * @date 2023-03-01
 */
public class MoneySerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator jGen, SerializerProvider provider) throws IOException {
        DecimalFormat df = new DecimalFormat("0.00");
        if (null == value) {
            //空值处理
            BigDecimal decimal = new BigDecimal("0.00");
            jGen.writeString(df.format(decimal));
        } else {
            jGen.writeString(df.format(value));
        }
    }
}
