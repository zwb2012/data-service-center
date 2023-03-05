package com.data.service.center.services.admin.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * description: 数据源配置
 *
 * @author wenbo.zhuang
 * @date 2022/02/17 16:49
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(value = {"com.data.service.center.dao.admin.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryPlatform")
public class DataSourceConfig {


    @Value("${spring.datasource.type}")
    Class<? extends DataSource> dataSourceType;


    @Bean(name = "dataSourcePlatform")
    @ConfigurationProperties(prefix = "spring.datasource.platform")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("sqlSessionFactoryPlatform")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourcePlatform") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return bean.getObject();
    }


    @Bean(name = "transactionManagerPlatform")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dataSourcePlatform") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplatePlatform")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactoryPlatform") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
