package com.balavignesh.restdemo.dao.impl;

import com.balavignesh.restdemo.dao.AccountDao;
import com.balavignesh.restdemo.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class AccountDaoImpl implements AccountDao {
    @Override
    public Stream<Account> getAccountList() {

        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        account.setAccountName("Bala");
        accountList.add(account);
        return accountList.stream();
    }

    @Override
    public Account getAccount(String accountIdentifier) {
        Account account = new Account();
        account.setAccountName("Bala");
        return account;
    }


    @Override
    public boolean deleteAccount(String accountIdentifier) {
        return true;
    }

    @Override
    public Account saveAccount(Account account) {
        account.setAccountIdentifier(000012345L);
        return account;
    }
}
