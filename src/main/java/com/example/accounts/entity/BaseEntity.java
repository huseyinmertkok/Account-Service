package com.example.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity {
    @Column(name = "CREATED_AT", updatable = false)
    private Date createdAt;
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;
    @Column(name = "UPDATED_AT", insertable = false)
    private Date updatedAt;
    @Column(name = "UPDATED_BY", insertable = false)
    private String updatedBy;
}
