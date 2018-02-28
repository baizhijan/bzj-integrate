package com.bzj.boot.confi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 配置类
 *
 * @author aaronbai
 * 目前所有配置统一配置,后续更具情况可以拆分
 * @create 2018-01-30 20:05
 **/
@Configuration
public class AllConfig {

    private Logger logger = LoggerFactory.getLogger(AllConfig.class);


    /** ============================ThreadPool配置=============================================*/

    @Value("${threadPool.corePoolSize} ")
    private int corePoolSize = 5;

    @Value("${threadPool.maxPoolSize}")
    private int maxPoolSize = 10;

    @Value("${threadPool.queueCapacity}")
    private int queueCapacity = 15;

    @Value("${threadPool.keepAlive}")
    private int keepAlive = 60;

    @Bean
    public ThreadPoolTaskExecutor myExecutor() {
        logger.info("ThreadPool init begin");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("dapExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(keepAlive);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(10);
        executor.initialize();
        logger.info("ThreadPool init success");
        return executor;
    }
}
