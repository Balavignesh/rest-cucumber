package com.balavignesh.restdemo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity {

    @Size(max=10, message="Added by should not be more than 10 characters")
    @Column(name = "added_by")
    private String addedBy;

    @Column(name = "add_ts")
    private Timestamp addedTimestamp;

    @Size(max=10, message="Updated By should not be more than 10 characters")
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_ts")
    private Timestamp updatedTimestamp;
}
