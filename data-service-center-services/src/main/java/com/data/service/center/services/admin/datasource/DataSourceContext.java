//package com.data.service.center.services.admin.datasource;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//import com.data.service.center.client.admin.entity.DataSourceConfigDO;
//import com.data.service.center.services.admin.service.DataSourceManageService;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//
///**
// * 数据源操作
// *
// * @author : wenbo.zhuang
// * @date: 2023/3/3 16:14
// **/
//// @Component
//public class DataSourceContext {
//
//    @Resource
//    private DynamicDataSource dynamicDataSource;
//    @Resource
//    private DataSourceManageService dataSourceManageService;
//
//    @SuppressWarnings("unchecked")
//    @PostConstruct
//    public void init() throws Exception {
//        List<DataSourceConfigDO> allDataSources = dataSourceManageService.getAllDataSources();
//
//        Map<Object, DataSource> oldDataSource = dynamicDataSource.getResolvedDataSources();
//        oldDataSource = oldDataSource == null ? new HashMap<>() : oldDataSource;
//
//        for (DataSourceConfigDO dataSourceConfig : allDataSources) {
//            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName(dataSourceConfig.getDataSourceType());
//            DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
//
//            oldDataSource.put(dataSourceConfig.getNameSpace(), dataSource);
//        }
//    }
//}
