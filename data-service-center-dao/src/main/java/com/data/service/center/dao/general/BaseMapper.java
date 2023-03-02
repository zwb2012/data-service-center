package com.data.service.center.dao.general;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 通用mapper
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:23:10
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T> {
}
