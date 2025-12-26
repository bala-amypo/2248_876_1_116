package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_calculations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;
    
    @Column(nullable = false)
    private Integer daysDelayed;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal calculatedPenalty;
    
    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime calculatedAt = LocalDateTime.now();
}