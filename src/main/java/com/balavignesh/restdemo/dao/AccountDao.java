package com.balavignesh.restdemo.dao;

import com.balavignesh.restdemo.domain.Account;

import java.util.Optional;
import java.util.stream.Stream;

public interface AccountDao {
    Stream<Account> getAccountList();

    Optional<Account> getAccount(Long accountIdentifier);

    void deleteAccount(Long accountIdentifier);

    Account saveAccount(Account account);
}
