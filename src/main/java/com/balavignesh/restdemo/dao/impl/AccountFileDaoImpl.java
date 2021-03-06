package com.balavignesh.restdemo.dao.impl;

import com.balavignesh.restdemo.dao.AccountFileDao;
import com.balavignesh.restdemo.dao.AccountFileRepository;
import com.balavignesh.restdemo.domain.AccountFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Repository
@Slf4j
public class AccountFileDaoImpl implements AccountFileDao {

    @Autowired
    AccountFileRepository accountFileRepository;

    @Override
    @Transactional
    public synchronized AccountFile saveAccountFile(AccountFile accountFile) {
        log.debug("Entering AccountFileDaoImpl saveAccountFile");
        accountFile.setAddedBy("APP");
        accountFile.setAddedTimestamp(new Timestamp(System.currentTimeMillis()));
        return accountFileRepository.save(accountFile);
    }
}
