package com.balavignesh.restdemo.dao;

import com.balavignesh.restdemo.domain.Account;
import com.balavignesh.restdemo.domain.AccountFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@Slf4j
@SpringBootTest
public class AccountFileDaoTest {

    @Autowired
    private AccountFileDao accountFileDao;

    @MockBean
    private AccountFileRepository accountFileRepository;

    @Test
    @DisplayName("Save an Account File")
    public void testSaveAccountFile(){
        log.debug("Entering Test for testSaveAccountFile");
        AccountFile accountFile = new AccountFile();
        accountFile.setFileName("TestFile");

        doReturn(accountFile).when(accountFileRepository).save(accountFile);
        AccountFile accountFileDb = accountFileDao.saveAccountFile(accountFile);
        log.debug(accountFileDb.toString());
        assertEquals(accountFile,accountFileDb);
    }
}
