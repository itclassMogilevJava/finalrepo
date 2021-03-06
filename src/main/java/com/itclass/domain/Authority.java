package com.itclass.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY")
@Data
public class Authority {

    @Id
    @Column(name = "NAME", length = 50)
    private String name;

}