package com.data.service.center.services.admin.config;

import java.util.HashMap;
import java.util.Map;

import com.data.service.center.services.admin.datasource.DynamicDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * description: 数据源配置
 *
 * @author wenbo.zhuang
 * @date 2022/02/17 16:49
 **/
@Configuration
@EnableTransactionManagement
public class DynamicDataSourceConfig {

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource source = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        source.setTargetDataSources(targetDataSources);
        return source;
    }

    @Bean("dynamicSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSourceConfig") @Autowired DynamicConfiguration configuration,
        @Qualifier("dynamicDataSource") @Autowired DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfiguration(configuration);
        bean.setDataSource(dynamicDataSource);
        return bean.getObject();
    }
}
