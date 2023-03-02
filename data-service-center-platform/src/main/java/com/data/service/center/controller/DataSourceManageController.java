package com.data.service.center.controller;

import com.data.service.center.client.admin.request.SqlRequest;
import com.data.service.center.client.general.entity.BaseResult;
import com.data.service.center.services.admin.service.SqlManageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wenbo.zhuang
 * @date 2022/11/05:20:02
 */
@RestController
@RequestMapping("/platform")
public class DataSourceManageController {

    @Resource
    private SqlManageService sqlManageService;

    @SuppressWarnings("rawtypes")
    @PostMapping
    public BaseResult addSql(@Validated @RequestBody SqlRequest sqlRequest) {
        sqlManageService.addSql(sqlRequest);
        return BaseResult.success();
    }
}
