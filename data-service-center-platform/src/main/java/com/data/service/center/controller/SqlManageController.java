package com.data.service.center.controller;

import com.data.service.center.client.admin.entity.SqlConfigDO;
import com.data.service.center.client.admin.request.SqlConfigRequest;
import com.data.service.center.client.general.entity.BaseResult;
import com.data.service.center.services.admin.service.SqlManageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * sql配置管理
 *
 * @author : wenbo.zhuang
 * @date: 2023/3/3 14:59
 **/
@RestController
@RequestMapping("/platform/sql")
public class SqlManageController {

    @Resource
    private SqlManageService sqlManageService;

    @PostMapping
    public BaseResult<Object> addSql(@Validated @RequestBody SqlConfigRequest sqlRequest) {
        sqlManageService.addSql(sqlRequest);
        return BaseResult.success();
    }

    @GetMapping("/getAll")
    public BaseResult<List<SqlConfigDO>> getAll() {
        return BaseResult.success(sqlManageService.getEfficientSql());
    }
}
