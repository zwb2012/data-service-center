package com.data.service.center.services.admin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.data.service.center.services.admin.tools.JsonSerializer;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * bean克隆
 *
 * @author wenbo.zhuang
 * @date 2022/06/16 17:51
 **/
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanCloneUtils {

    public static <T> T clone(Object src, Class<T> clazz) {
        return src != null && clazz != null ? JsonSerializer.convertValue(src, TypeFactory.defaultInstance().constructType(clazz)) : null;
    }


    public static <T> T clone(Object src, JavaType type) {
        return src != null && type != null ? JsonSerializer.convertValue(src, type) : null;
    }

    public static <T>  List<T> cloneList(Object src, JavaType type) {
        return clone(src, JsonSerializer.toJavaType(type));
    }

    public static <T> List<T> cloneList(Object src, Class<T> clazz) {
        // 多层内嵌 TypeReference< BaseResult<List<T>>> type = new TypeReference<BaseResult<List<T>>>(BaseResult.class, List.class, returnClazz) { };
        return clone(src, JsonSerializer.toJavaType(new TypeReference<List<T>>(clazz) {
        }.getType()));
    }
}
