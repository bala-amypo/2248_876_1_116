package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
@Tag(name = "Delivery Record Management", description = "APIs for managing delivery records")
public class DeliveryRecordController {

    @Autowired
    private DeliveryRecordService deliveryRecordService;

    @PostMapping
    @Operation(summary = "Create a new delivery record")
    public ResponseEntity<DeliveryRecord> createDeliveryRecord(@RequestBody DeliveryRecord record) {
        DeliveryRecord created = deliveryRecordService.createDeliveryRecord(record);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get delivery record by ID")
    public ResponseEntity<DeliveryRecord> getDeliveryRecord(@PathVariable Long id) {
        DeliveryRecord record = deliveryRecordService.getRecordById(id);
        return ResponseEntity.ok(record);
    }

    @GetMapping("/contract/{contractId}")
    @Operation(summary = "Get delivery records for contract")
    public ResponseEntity<List<DeliveryRecord>> getDeliveryRecordsForContract(@PathVariable Long contractId) {
        List<DeliveryRecord> records = deliveryRecordService.getDeliveryRecordsForContract(contractId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/contract/{contractId}/latest")
    @Operation(summary = "Get latest delivery record for contract")
    public ResponseEntity<DeliveryRecord> getLatestDeliveryRecord(@PathVariable Long contractId) {
        DeliveryRecord record = deliveryRecordService.getLatestDeliveryRecord(contractId);
        return ResponseEntity.ok(record);
    }
}