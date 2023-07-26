package com.data.service.center.services.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.data.service.center.client.admin.entity.SqlConfigDO;
import com.data.service.center.client.admin.enums.SqlAndSourceStatusEnum;
import com.data.service.center.dao.admin.mapper.SqlManageMapper;
import com.data.service.center.services.admin.annotion.SqlRefresh;
import com.data.service.center.services.admin.service.SqlManageService;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

/**
 * sql管理实现类
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:22:38
 */
@Service
public class SqlManageServiceImpl implements SqlManageService {

    @Resource
    private SqlManageMapper sqlManageMapper;

    @SqlRefresh
    @Override
    public void addSql(SqlConfigDO sqlConfigDO) {
        sqlManageMapper.insertSelective(sqlConfigDO);
    }

    @Override
    public List<SqlConfigDO> getEfficientSql() {
        Example example = new Example(SqlConfigDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", SqlAndSourceStatusEnum.EFFECTIVE.getStatus());
        return sqlManageMapper.selectByCondition(example);
    }
}
