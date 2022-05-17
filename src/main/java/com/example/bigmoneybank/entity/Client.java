package com.example.bigmoneybank.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;


import java.math.BigDecimal;

@Table("client")
@Data
public class Client {

    @Id
    @Column("id")
    private Integer id;

    @Column("balance")
    private BigDecimal balance;
}