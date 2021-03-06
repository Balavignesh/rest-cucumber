package com.balavignesh.restdemo.domain;

import com.balavignesh.restdemo.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACCOUNT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountIdentifier;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_status")
    private boolean accountStatus;

}
