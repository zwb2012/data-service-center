package com.data.service.center.controller;

import java.util.List;

import javax.annotation.Resource;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import com.data.service.center.client.admin.request.DataSourceConfigRequest;
import com.data.service.center.client.general.entity.BaseResult;
import com.data.service.center.client.general.enums.SqlAndSourceStatusEnum;
import com.data.service.center.services.admin.service.DataSourceManageService;
import com.data.service.center.services.admin.tools.DataSourceConfigChecker;
import com.data.service.center.services.admin.utils.BeanCloneUtils;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenbo.zhuang
 * @date 2022/11/05:20:02
 */
@RestController
@RequestMapping("/platform/dataSource")
public class DataSourceManageController {

    @Resource
    private DataSourceManageService dataSourceManageService;

    @PostMapping("addDataSource")
    public BaseResult<Object> addDataSource(@Validated @RequestBody DataSourceConfigRequest dataSourceConfigRequest) {
        DataSourceConfigDO sourceConfigDO = BeanCloneUtils.clone(dataSourceConfigRequest, DataSourceConfigDO.class);
        // 默认数据源配置有效性
        sourceConfigDO.setStatus(SqlAndSourceStatusEnum.EFFECTIVE.getStatus());
        // 数据库配置检查
        DataSourceConfigChecker.checkerDataSourceConfig(sourceConfigDO);
        dataSourceManageService.addDataSource(sourceConfigDO);
        return BaseResult.success();
    }



    @GetMapping("/getAll")
    public BaseResult<List<DataSourceConfigDO>> getAll() {
        return BaseResult.success(dataSourceManageService.getEfficientDataSources());
    }
}
