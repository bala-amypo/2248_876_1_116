package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String ruleName;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyPerDay;
    
    @Column(nullable = false)
    private Double maxPenaltyPercentage;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean isDefaultRule = false;
    
    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}