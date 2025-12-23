package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String contractNumber;

    @Column(nullable = false)
    private BigDecimal contractValue;

    @Column(nullable = false)
    private boolean active;

    public Contract() {}

    public Long getId() {
        return id;
    }

    // 🔴 THIS WAS MISSING — NOW FIXED
    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public BigDecimal getContractValue() {
        return contractValue;
    }

    public void setContractValue(BigDecimal contractValue) {
        this.contractValue = contractValue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
