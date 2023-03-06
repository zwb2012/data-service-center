package com.data.service.center.services.admin.aspects;

import javax.annotation.Resource;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import com.data.service.center.client.admin.entity.SqlConfigDO;
import com.data.service.center.services.admin.datasource.DynamicDataSourceContext;
import com.data.service.center.services.admin.datasource.DynamicSqlContext;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 数据源重刷新切面
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/6 14:21
 **/
@Slf4j
@Aspect
@Component
public class DataSourceRefreshAspect {

    @Resource
    private DynamicDataSourceContext dynamicDataSourceContext;

    @Pointcut("@annotation(com.data.service.center.services.admin.annotion.DataSourceRefresh)")
    public void pointCut() {}

    @After("pointCut()")
    public void after(JoinPoint jp) {
        Object[] args = jp.getArgs();
        if (null == args || !(args[0] instanceof DataSourceConfigDO)) {
            return;
        }
        DataSourceConfigDO dataSourceConfigDO = (DataSourceConfigDO) args[0];
        dynamicDataSourceContext.addTask(dataSourceConfigDO);
    }
}
