package com.data.service.center.services.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.data.service.center.client.admin.entity.SqlConfigDO;
import com.data.service.center.client.admin.request.SqlConfigRequest;
import com.data.service.center.client.general.enums.SqlAndSourceStatusEnum;
import com.data.service.center.dao.admin.mapper.SqlManageMapper;
import com.data.service.center.services.admin.service.SqlManageService;
import com.data.service.center.services.admin.tools.SqlConfigChecker;
import com.data.service.center.services.admin.utils.BeanCloneUtils;

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

    @Override
    public int addSql(SqlConfigRequest sqlRequest) {
        SqlConfigDO sqlDO = BeanCloneUtils.clone(sqlRequest, SqlConfigDO.class);
        // sql配置检查, 并确定执行sql类型
        SqlConfigChecker.checkerSqlConfig(sqlDO);
        // 默认sql配置有效性
        sqlDO.setStatus(SqlAndSourceStatusEnum.EFFECTIVE.getStatus());
        return sqlManageMapper.insertSelective(sqlDO);
    }

    @Override
    public List<SqlConfigDO> getEfficientSql() {
        Example example = new Example(SqlConfigDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", SqlAndSourceStatusEnum.EFFECTIVE.getStatus());
        return sqlManageMapper.selectByCondition(example);
    }
}
