package com.example.demo.service.implement;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRecordRepository;
    private final ContractRepository contractRepository;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRecordRepository,
                                     ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {

        if (record == null) {
            throw new ApiException("Delivery record cannot be null");
        }

        if (record.getContract() == null || record.getContract().getId() == null) {
            throw new ApiException("Contract is required");
        }

        Contract contract = contractRepository.findById(record.getContract().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        record.setContract(contract);

        return deliveryRecordRepository.save(record);
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return deliveryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery record not found"));
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {

        if (!contractRepository.existsById(contractId)) {
            throw new ResourceNotFoundException("Contract not found");
        }

        return deliveryRecordRepository.findByContractId(contractId);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {

        return getDeliveryRecordsForContract(contractId).stream()
                .max(Comparator.comparing(DeliveryRecord::getDeliveryDate))
                .orElseThrow(() -> new ResourceNotFoundException("No delivery records found"));
    }
}
