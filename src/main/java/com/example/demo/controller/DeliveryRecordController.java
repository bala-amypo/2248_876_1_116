package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryRecordService;

    // ✅ Constructor Injection (MANDATORY)
    public DeliveryRecordController(DeliveryRecordService deliveryRecordService) {
        this.deliveryRecordService = deliveryRecordService;
    }

    // ✅ POST /api/delivery-records
    // Creates delivery record
    @PostMapping
    public ResponseEntity<DeliveryRecord> createDeliveryRecord(
            @RequestBody DeliveryRecord deliveryRecord) {

        DeliveryRecord savedRecord =
                deliveryRecordService.createDeliveryRecord(deliveryRecord);

        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    // ✅ GET /api/delivery-records/{id}
    // Retrieves specific record
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRecord> getRecordById(@PathVariable Long id) {

        DeliveryRecord record =
                deliveryRecordService.getRecordById(id);

        return ResponseEntity.ok(record);
    }

    // ✅ GET /api/delivery-records/contract/{contractId}
    // Gets all records for a contract
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<DeliveryRecord>> getRecordsForContract(
            @PathVariable Long contractId) {

        List<DeliveryRecord> records =
                deliveryRecordService.getDeliveryRecordsForContract(contractId);

        return ResponseEntity.ok(records);
    }

    // ✅ GET /api/delivery-records/contract/{contractId}/latest
    // Gets latest delivery record
    @GetMapping("/contract/{contractId}/latest")
    public ResponseEntity<DeliveryRecord> getLatestDeliveryRecord(
            @PathVariable Long contractId) {

        DeliveryRecord latestRecord =
                deliveryRecordService.getLatestDeliveryRecord(contractId);

        return ResponseEntity.ok(latestRecord);
    }
}
