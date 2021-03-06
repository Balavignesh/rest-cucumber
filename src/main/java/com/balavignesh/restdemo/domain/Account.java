package com.balavignesh.restdemo.domain;

import lombok.Data;

@Data
public class Account {

    private String accountIdentifier;

    private String accountName;

    private String accountType;

    private boolean accountStatus;

}
