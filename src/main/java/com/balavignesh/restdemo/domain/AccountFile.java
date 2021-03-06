package com.balavignesh.restdemo.domain;

import com.balavignesh.restdemo.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT_FILE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private Long fileIdentifier;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file")
    private byte[] fileBytes;
}
