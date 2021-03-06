package com.balavignesh.restdemo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Account")
public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String id) {
        super("Account not found : " + id);
    }
}
