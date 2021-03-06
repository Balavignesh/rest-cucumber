package com.balavignesh.restdemo.services;

import com.balavignesh.restdemo.dao.AccountDao;
import com.balavignesh.restdemo.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api")
@Slf4j
public class AccountResource {

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/account")
    @ResponseBody
    public List<Account> getAccountList() throws IOException {
        log.debug("Entering AccountResource getAccountList");
        Stream<Account> accountStream =accountDao.getAccountList();
        return Optional.ofNullable(accountStream).orElseGet(Stream::empty)
                .filter(Objects::nonNull).collect(Collectors.toList());

    }

    @GetMapping("/account/{accountIdentifier}")
    @ResponseBody
    public ResponseEntity<Account> getAccount(@PathVariable Long accountIdentifier) throws IOException {
        log.debug("Entering AccountResource getAccount {}",accountIdentifier);
        Optional<Account> account =accountDao.getAccount(accountIdentifier);
        return ResponseEntity.ok().body(account
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Account Not Found")));

    }
    @PostMapping(value="/account")
    ResponseEntity<?> createAccount( @Valid @RequestBody Account account) {
        log.debug("Entering AccountResource createAccount Account Name {}" +
                " Account Type {}",account.getAccountName(),account.getAccountType());
        Account accountSaved = accountDao.saveAccount(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountSaved.getAccountIdentifier())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping(value="/account/{accountIdentifier}")
    ResponseEntity<String> deleteAccount(@PathVariable Long accountIdentifier) {
        log.debug("Entering AccountResource deleteAccount {}",accountIdentifier);
        Optional<Account> account =accountDao.getAccount(accountIdentifier);
        account.orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account Not Found"));
        accountDao.deleteAccount(accountIdentifier);
        return ResponseEntity.ok().body("Account deleted");
    }

}
