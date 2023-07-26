package com.data.service.center;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring AOP 默认使用 JDK 动态代理 <br/>
 * proxyTargetClass = true 时则代理目标对象时强制使用 CGLIB 代理 <br/>
 * exposeProxy = true 暴露代理对象，这样就可以使用 AopContext.currentProxy() 方法获取当前代理的对象<br/>
 * 可以解决在方法里面调方法，或者用 this 关键字调方法，而无法被代理的情况
 *
 * @author wenbo.zhuang
 * @date 2022/11/05:19:28
 */
@Slf4j
//@EnableDiscoveryClient
@SpringBootApplication
//@EnableDubbo
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MainApplication {

    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled", "false");
        System.setProperty("dubbo.application.logger", "slf4j");

        SpringApplication.run(MainApplication.class, args);
    }
}
