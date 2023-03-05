package com.data.service.center;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.extern.slf4j.Slf4j;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wenbo.zhuang
 * @date 2022/11/05:19:28
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@EnableDubbo
public class MainApplication {

    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled","false");
        System.setProperty("dubbo.application.logger","slf4j");

        SpringApplication.run(MainApplication.class, args);
    }

}
