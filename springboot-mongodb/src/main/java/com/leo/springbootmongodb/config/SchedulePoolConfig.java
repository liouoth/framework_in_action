package com.leo.springbootmongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class SchedulePoolConfig {
    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        return Executors.newScheduledThreadPool(5);
    }
}
