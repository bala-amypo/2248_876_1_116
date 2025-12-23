package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.example.demo.exception.ApiException;

@Entity
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    private String notes;

    // ✅ No-args constructor
    public DeliveryRecord() {
    }

    // ✅ Core fields constructor
    public DeliveryRecord(Contract contract, LocalDate deliveryDate, String notes) {
        this.contract = contract;
        setDeliveryDate(deliveryDate);
        this.notes = notes;
    }

    // ✅ FUTURE DATE VALIDATION
    public void setDeliveryDate(LocalDate deliveryDate) {
        if (deliveryDate == null) {
            throw new ApiException("Delivery date is required");
        }
        if (deliveryDate.isAfter(LocalDate.now())) {
            throw new ApiException("Delivery date cannot be in the future");
        }
        this.deliveryDate = deliveryDate;
    }

    // ---------- GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
