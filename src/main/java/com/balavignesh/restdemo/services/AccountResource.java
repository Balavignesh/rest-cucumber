package com.balavignesh.restdemo.services;

import com.balavignesh.restdemo.dao.AccountDao;
import com.balavignesh.restdemo.domain.Account;
import javassist.NotFoundException;
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
        Stream<Account> accountLists =accountDao.getAccountList();
        return Optional.ofNullable(accountLists).orElseGet(Stream::empty)
                .filter(Objects::nonNull).collect(Collectors.toList());

    }

    @GetMapping("/account/{accountIdentifier}")
    @ResponseBody
    public ResponseEntity<Account> getAccount(@PathVariable String accountIdentifier) throws IOException {
        Account account =accountDao.getAccount(accountIdentifier);
        return ResponseEntity.ok().body(Optional.ofNullable(account)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Account Not Found")));

    }
    @PostMapping(value="/account")
    ResponseEntity<?> create( @Valid @RequestBody Account account) {

        Account accountSaved = accountDao.saveAccount(account);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountSaved.getAccountIdentifier())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping(value="/account/{accountIdentifier}")
    ResponseEntity<String> delete(@PathVariable String accountIdentifier) {
        Account account =accountDao.getAccount(accountIdentifier);
        Optional.ofNullable(account)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account Not Found"));
        boolean accountDeleteStatus = accountDao.deleteAccount(accountIdentifier);
        return accountDeleteStatus ? ResponseEntity.ok().body("Account deleted with success!")
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Account Not Deleted");
    }

}
