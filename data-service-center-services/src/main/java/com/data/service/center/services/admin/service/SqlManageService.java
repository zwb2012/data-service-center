package com.data.service.center.services.admin.service;

import com.data.service.center.client.admin.entity.SqlConfigDO;
import com.data.service.center.client.admin.request.SqlConfigRequest;

import java.util.List;

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
     * @param sqlRequest sql配置内容
     * @return 影响行数
     */
    int addSql(SqlConfigRequest sqlRequest);

    /**
     * 获取有效的sql配置
     *
     * @return List<SqlDO> sql列表
     */
    List<SqlConfigDO> getEfficientSql();
}