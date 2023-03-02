package com.data.service.center.services.admin.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * description: 数据源配置
 *
 * @author wenbo.zhuang
 * @date 2022/02/17 16:49
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(value = {"com.data.service.center.dao"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {


    @Value("${spring.datasource.type}")
    Class<? extends DataSource> dataSourceType;


    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.platform")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    // @Bean
    // public DynamicDataSource dynamicDataSource() {
    // DynamicDataSource source = new DynamicDataSource();
    //
    // Map<Object, Object> targetDataSources = new HashMap<>();
    // targetDataSources.put("platform", dataSource());
    // source.setTargetDataSources(targetDataSources);
    // return source;
    // }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource,
        @Autowired DynamicConfiguration configuration) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfiguration(configuration);
        // bean.setDataSource(dynamicDataSource());
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return bean.getObject();
    }

    // public static class DynamicDataSource extends AbstractRoutingDataSource {
    // @Override
    // protected Object determineCurrentLookupKey() {
    // return "platform";
    // }
    // }
}
