package com.balavignesh.restdemo.services;

import com.balavignesh.restdemo.dao.AccountDao;
import com.balavignesh.restdemo.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@Slf4j
@SpringBootTest
public class AccountResourceTest {

    @Autowired
    private AccountResource accountResource;

    @MockBean
    private AccountDao accountDao;

    @BeforeAll
    static void header(){
        log.debug("Starting Testing AccountResourceTest");
    }
    @AfterAll
    static void footer(){
        log.debug("Ended Testing AccountResourceTest");
    }

    @Test
    @DisplayName("Testing Get All Account List Resource")
    public void testGetAccountList() throws IOException {
        log.debug("Entering Test for testGetAccountList");
        doReturn(getAccountList()).when(accountDao).getAccountList();
        List<Account> accountDBList = accountResource.getAccountList();
        assertEquals(2,accountDBList.size());
    }

    private Account getAccount(){
        return getAccountList().filter(list -> list.getAccountIdentifier() == 100L).
                findFirst().get();
    }

    private Stream<Account> getAccountList(){
        Account account = new Account(100L, "Account Test",
                "DDA", true);
        Account account2 = new Account(200L, "Account Test",
                "DDA", true);
        return Stream.of(account,account2);
    }

    @Test
    @DisplayName("Testing Get Account Resource")
    public void testGetAccount() throws IOException {
        log.debug("Entering Test for testGetAccountList");
        doReturn(Optional.of(getAccount())).when(accountDao).getAccount(100L);
        ResponseEntity<Account> account = accountResource.getAccount(100L);
        assertEquals(HttpStatus.OK,account.getStatusCode());
    }

    @Test
    @DisplayName("Testing Get Account Resource with Account Not Found")
    public void testGetAccountWithAccountNotFound(){
        log.debug("Entering Test for testGetAccountWithAccountNotFound");
        doReturn(Optional.of(getAccount())).when(accountDao).getAccount(100L);
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> accountResource.getAccount(300L));
        assertTrue(exception.getReason().contains("Account Not Found"));
    }

    @Test
    @DisplayName("Save an Account Resource")
    public void testSaveAccount(){
        log.debug("Entering Test for testSaveAccount");

        doReturn(getAccount()).when(accountDao).saveAccount(getAccount());
        ResponseEntity account = accountResource.createAccount(getAccount());
        log.debug(account.toString());
        assertEquals(HttpStatus.CREATED,account.getStatusCode());
    }

    @Test
    @DisplayName("Delete an Account Resource")
    public void testDeleteAccount(){
        log.debug("Entering Test for testDeleteAccount");
        doReturn(Optional.of(getAccount())).when(accountDao).getAccount(100L);
        doNothing().when(accountDao).deleteAccount(100L);
        accountResource.deleteAccount(100L);
    }

    @Test
    @DisplayName("Delete an Account Resource with Account Not Found")
    public void testDeleteAccountWithAccountNotFound(){
        log.debug("Entering Test for testDeleteAccountWithAccountNotFound");
        doNothing().when(accountDao).deleteAccount(100L);
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> accountResource.deleteAccount(100L));
        assertTrue(exception.getReason().contains("Account Not Found"));
    }


}
