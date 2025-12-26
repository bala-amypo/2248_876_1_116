package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordService {
    DeliveryRecord createDeliveryRecord(DeliveryRecord record);
    DeliveryRecord getRecordById(Long id);
    DeliveryRecord getLatestDeliveryRecord(Long contractId);
    List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId);
}