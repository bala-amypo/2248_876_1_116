package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;
    
    @Column(nullable = false)
    private Integer daysDelayed;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyAmount;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt = LocalDateTime.now();
}