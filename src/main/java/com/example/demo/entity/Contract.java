package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractNumber;
    private BigDecimal baseContractValue;

    public Contract() {}

    public Long getId() {
        return id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public BigDecimal getBaseContractValue() {
        return baseContractValue;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setBaseContractValue(BigDecimal baseContractValue) {
        this.baseContractValue = baseContractValue;
    }
}
