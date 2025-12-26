package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String contractNumber;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String counterpartyName;
    
    @Column(nullable = false)
    private LocalDate agreedDeliveryDate;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal baseContractValue;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}