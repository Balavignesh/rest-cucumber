package com.balavignesh.restdemo.dao;

import com.balavignesh.restdemo.domain.Account;
import com.balavignesh.restdemo.domain.AccountFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@Slf4j
@SpringBootTest
public class AccountFileDaoTest {

    @Autowired
    private AccountFileDao accountFileDao;

    @MockBean
    private AccountFileRepository accountFileRepository;

    @BeforeAll
    static void header(){
        log.debug("Starting Testing AccountFileDaoTest");
    }
    @AfterAll
    static void footer(){
        log.debug("Ended Testing AccountFileDaoTest");
    }

    @Test
    @DisplayName("Save an Account File")
    public void testSaveAccountFile(){
        log.debug("Entering Test for testSaveAccountFile");
        AccountFile accountFile = new AccountFile
                (100L,"TestFile",null);
        accountFile.setFileIdentifier(200L);
        accountFile.setFileName("TestFile 2");
        accountFile.setFileBytes(null);
        accountFile.setAddedTimestamp(new Timestamp(System.currentTimeMillis()));
        accountFile.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
        log.debug(accountFile.hashCode()+"");
        log.debug(accountFile.equals(accountFile)+"");
        doReturn(accountFile).when(accountFileRepository).save(accountFile);
        AccountFile accountFileDb = accountFileDao.saveAccountFile(accountFile);
        log.debug(accountFileDb.toString());
        log.debug(accountFileDb.getFileName());
        log.debug(accountFileDb.getAddedBy());
        log.debug(accountFileDb.getUpdatedBy());
        log.debug(accountFileDb.getAddedTimestamp().toString());
        log.debug(accountFileDb.getUpdatedTimestamp().toString());
        assertEquals(accountFile,accountFileDb);
    }
}
