package com.balavignesh.restdemo.dao;

import com.balavignesh.restdemo.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootTest
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @MockBean
    private AccountRepository accountRepository;

    @BeforeAll
    static void header(){
        log.debug("Starting Testing AccountDaoTest");
    }
    @AfterAll
    static void footer(){
        log.debug("Ended Testing AccountDaoTest");
    }

    @Test
    @DisplayName("Testing Get All Account List")
    public void testGetAccountList(){
        log.debug("Entering Test for testGetAccountList");
        doReturn(getAccountList().collect(Collectors.toList())).when(accountRepository).findAll();
        Stream<Account> accountDBList = accountDao.getAccountList();
        assertEquals(2,accountDBList.count());
    }

    private Account getAccount(){
        return getAccountList().filter(list -> list.getAccountIdentifier() == 100L).
                findFirst().get();
    }

    private Stream<Account> getAccountList(){
        Account account = new Account(100L, "Account Test",
                "DDA", true);

        Account account2 = new Account();
        account2.setAccountIdentifier(200L);
        account2.setAccountName("Account Test2");
        account2.setAccountType("C3");
        account2.setAccountStatus(true);
        account2.setAddedBy("TEST");
        account2.setUpdatedBy("TEST");
        account2.setAddedTimestamp(new Timestamp(System.currentTimeMillis()));
        account2.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));

        return Stream.of(account,account2);
    }

    @Test
    @DisplayName("Testing Get Account")
    public void testGetAccount(){
        log.debug("Entering Test for testGetAccountList");
        doReturn(Optional.of(getAccount())).when(accountRepository).findById(100L);
        Optional<Account> account = accountDao.getAccount(100L);
        assertEquals(true,account.isPresent());
    }

    @Test
    @DisplayName("Save an Account")
    public void testSaveAccount(){
        log.debug("Entering Test for testSaveAccount");

        doReturn(getAccount()).when(accountRepository).save(getAccount());
        Account account = accountDao.saveAccount(getAccount());
        assertEquals(getAccount(),account);
    }



    @Test
    @DisplayName("Delete an Account")
    public void testDeleteAccount(){
        log.debug("Entering Test for testDeleteAccount");
        doNothing().when(accountRepository).deleteById(100L);
        accountDao.deleteAccount(100L);
    }
}
