package com.balavignesh.restdemo.dao.impl;

import com.balavignesh.restdemo.dao.AccountDao;
import com.balavignesh.restdemo.dao.AccountRepository;
import com.balavignesh.restdemo.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Slf4j
public class AccountDaoImpl implements AccountDao {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Stream<Account> getAccountList() {
        return accountRepository.findAll().stream();
    }

    @Override
    public Optional<Account> getAccount(Long accountIdentifier) {
        return accountRepository.findById(accountIdentifier);
    }

    @Override
    public void deleteAccount(Long accountIdentifier) {
        accountRepository.deleteById(accountIdentifier);
    }

    @Override
    @Transactional
    public synchronized Account saveAccount(Account account) {
        log.debug("Entering AccountDaoImpl saveAccount");
        account.setAddedBy("APP");
        account.setAddedTimestamp(new Timestamp(System.currentTimeMillis()));
        return accountRepository.save(account);
    }
}
