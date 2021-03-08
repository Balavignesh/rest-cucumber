package com.balavignesh.restdemo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity {

    @Column(name = "added_by")
    private String addedBy;

    @Column(name = "add_ts")
    private Timestamp addedTimestamp;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_ts")
    private Timestamp updatedTimestamp;
}
