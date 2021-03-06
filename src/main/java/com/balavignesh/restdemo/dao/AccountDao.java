package com.balavignesh.restdemo.dao;

import com.balavignesh.restdemo.domain.Account;

import java.util.List;
import java.util.stream.Stream;

public interface AccountDao {
    Stream<Account> getAccountList();

    Account getAccount(String accountIdentifier);

    boolean deleteAccount(String accountIdentifier);

    Account saveAccount(Account account);
}
