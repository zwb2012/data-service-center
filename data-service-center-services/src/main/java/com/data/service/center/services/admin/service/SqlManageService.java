package com.data.service.center.services.admin.service;

import java.util.List;

import com.data.service.center.client.admin.entity.SqlConfigDO;

/**
 * sql管理
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:22:38
 */
public interface SqlManageService {

    /**
     * 增加执行的sql
     *
     * @param sqlConfigDO sql配置内容
     */
    void addSql(SqlConfigDO sqlConfigDO);

    /**
     * 获取有效的sql配置
     *
     * @return List<SqlDO> sql列表
     */
    List<SqlConfigDO> getEfficientSql();
}
