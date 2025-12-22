package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Contract contract;
    @ManyToOne private DeliveryRecord deliveryRecord;
    @ManyToOne private BreachRule breachRule;

    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;
    private LocalDateTime calculatedAt;

    @PrePersist
    void onCalc() {
        calculatedAt = LocalDateTime.now();
    }

    public PenaltyCalculation() {}
}
