package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.DeliveryRecord;

public interface DeliveryRecordService {

    DeliveryRecord createDeliveryRecord(DeliveryRecord record);

    DeliveryRecord getRecordById(Long id);

    List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId);

    DeliveryRecord getLatestDeliveryRecord(Long contractId);
}
