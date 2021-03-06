package com.balavignesh.restdemo;

import com.balavignesh.restdemo.dao.AccountDao;
import com.balavignesh.restdemo.dao.AccountFileDao;
import com.balavignesh.restdemo.domain.Account;
import com.balavignesh.restdemo.domain.AccountFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.SerializationUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableScheduling
@Slf4j
public class CronScheduler {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountFileDao accountFileDao;

    @Scheduled(cron = "${cron.generate.file.expression}")
    public void scheduleTaskUsingCronExpression() {

        log.info("Scheduler starter - " + new Date().toString());
        Stream<Account> accountStream = accountDao.getAccountList();
        AccountFile accountFile = new AccountFile();
        accountFile.setFileName(getFileName());
        accountFile.setFileBytes(SerializationUtils.serialize(Optional.ofNullable(accountStream)
                .orElseGet(Stream::empty)
                .filter(Objects::nonNull).collect(Collectors.toList())));;
        accountFileDao.saveAccountFile(accountFile);

    }
    private synchronized String getFileName() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS").format(new Date());
        return "AccountList_" + timeStamp + ".txt";
    }

}
