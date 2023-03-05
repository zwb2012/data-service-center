package com.data.service.center.controller;

import java.util.List;

import javax.annotation.Resource;

import com.data.service.center.client.admin.entity.DataSourceConfigDO;
import com.data.service.center.client.general.entity.BaseResult;
import com.data.service.center.services.admin.service.DataSourceManageService;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getAll")
    public BaseResult<List<DataSourceConfigDO>> getAll() {
        return BaseResult.success(dataSourceManageService.getAllDataSources());
    }
}
