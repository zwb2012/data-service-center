package com.data.service.center.services.admin.config;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @author wenbo.zhuang
 * @date 2022/11/18:0:53
 */
public class GlobalExecutor {

    private final static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.setName("com.data.service.center.timer");
        return t;
    });

    public static void submit(Runnable runnable) {
        executorService.submit(runnable);
    }
}
