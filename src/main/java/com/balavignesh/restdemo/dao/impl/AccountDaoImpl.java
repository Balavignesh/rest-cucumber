package com.balavignesh.restdemo.dao.impl;

import com.balavignesh.restdemo.dao.AccountDao;
import com.balavignesh.restdemo.dao.AccountRepository;
import com.balavignesh.restdemo.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
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
    public Account saveAccount(Account account) {
        account.setAddedBy("APP");
        account.setAddedTimestamp(new Timestamp(System.currentTimeMillis()));
        return accountRepository.save(account);
    }
}
