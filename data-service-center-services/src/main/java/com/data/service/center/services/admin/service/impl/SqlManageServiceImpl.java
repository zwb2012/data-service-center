package com.data.service.center.services.admin.service.impl;

import com.data.service.center.client.admin.entity.SqlDO;
import com.data.service.center.client.admin.request.SqlRequest;
import com.data.service.center.client.general.enums.SqlAndSourceStatusEnum;
import com.data.service.center.dao.admin.mapper.SqlManageMapper;
import com.data.service.center.services.admin.service.SqlManageService;
import com.data.service.center.services.admin.utils.BeanCloneUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public int addSql(SqlRequest sqlRequest) {
        SqlDO sqlDO = BeanCloneUtils.clone(sqlRequest, SqlDO.class);

        return sqlManageMapper.insertSelective(sqlDO);
    }

    @Override
    public List<SqlDO> getEfficientSql() {
        SqlDO sqlDO = new SqlDO();
        sqlDO.setStatus(SqlAndSourceStatusEnum.EFFECTIVE.getStatus());
        return sqlManageMapper.selectByCondition(sqlDO);
    }
}
