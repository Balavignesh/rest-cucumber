package com.balavignesh.restdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
@Slf4j
public class CronScheduler {

    @Scheduled(cron = "${cron.generate.file.expression}")
    public void scheduleTaskUsingCronExpression() {
        log.info("Scheduler starter - " + new Date().toString());
    }
}
