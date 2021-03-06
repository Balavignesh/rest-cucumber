package com.balavignesh.restdemo.domain;

import com.balavignesh.restdemo.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountIdentifier;

    @NotNull
    @Size(max=50, message="Account Name should not be more than 50 characters")
    @Column(name = "account_name")
    private String accountName;

    @NotNull
    @Size(max=10, message="Account Type should not be more than 10 characters")
    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_status")
    private boolean accountStatus;

}
