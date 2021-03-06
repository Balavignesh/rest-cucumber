package com.balavignesh.restdemo.domain;

import lombok.Data;

@Data
public class Account {

    private Long accountIdentifier;

    private String accountName;

    private String accountType;

    private boolean accountStatus;

}
