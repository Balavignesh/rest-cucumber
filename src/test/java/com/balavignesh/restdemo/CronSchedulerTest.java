package com.balavignesh.restdemo;

import com.balavignesh.restdemo.dao.AccountDao;
import com.balavignesh.restdemo.dao.AccountFileDao;
import com.balavignesh.restdemo.domain.Account;
import com.balavignesh.restdemo.domain.AccountFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.stream.Stream;

import static org.mockito.Mockito.doReturn;

@Slf4j
@SpringBootTest
public class CronSchedulerTest {

    @Autowired
    private CronScheduler cronScheduler;

    @MockBean
    private AccountDao accountDao;

    @MockBean
    private AccountFileDao accountFileDao;

    @Test
    @DisplayName("Testing Cron schedule")
    public void testScheduleTaskUsingCronExpression() throws IOException {
        log.debug("Entering Test for testScheduleTaskUsingCronExpression");
        doReturn(getAccountList()).when(accountDao).getAccountList();
        doReturn(getAccountFile()).when(accountFileDao).saveAccountFile(getAccountFile());
        cronScheduler.scheduleTaskUsingCronExpression();
    }
    private Stream<Account> getAccountList(){
        Account account = new Account(100L, "Account Test",
                "DDA", true);
        Account account2 = new Account(200L, "Account Test 2",
                "C3", false);
        return Stream.of(account,account2);
    }
    private AccountFile getAccountFile(){
        AccountFile accountFile = new AccountFile();
        return accountFile;
    }
}
