package com.itclass.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "goods")
public class Good {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Double price;
}
