package com.example.demo.entity;

import java.time.LocalDate;

import com.example.demo.exception.ApiException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_records")
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    private String notes;

    // ✅ No-args constructor
    public DeliveryRecord() {
    }

    // ✅ Core fields constructor
    public DeliveryRecord(Contract contract, LocalDate deliveryDate) {
        this.contract = contract;
        setDeliveryDate(deliveryDate);
    }

    // ✅ Getters and Setters

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

    // ✅ Rule: deliveryDate cannot be in the future
    public void setDeliveryDate(LocalDate deliveryDate) {
        if (deliveryDate == null) {
            throw new ApiException("deliveryDate cannot be null");
        }

        if (deliveryDate.isAfter(LocalDate.now())) {
            throw new ApiException("deliveryDate cannot be in the future");
        }

        this.deliveryDate = deliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
