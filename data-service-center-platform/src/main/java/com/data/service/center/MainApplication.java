package com.data.service.center;

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
        SpringApplication.run(MainApplication.class, args);
    }

}
