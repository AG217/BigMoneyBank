package com.example.bigmoneybank.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Table("client")
@Entity
public class Client {
    @Id
    @Column("id")
    private Long id;

    @Column("balance")
    private BigDecimal balance;

    public Client() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}