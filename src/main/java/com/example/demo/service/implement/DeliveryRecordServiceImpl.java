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

    // ✅ Constructor Injection (MANDATORY for your test)
    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRecordRepository,
                                     ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }

    // ✅ Create delivery record with date validation
    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {

        if (record == null) {
            throw new ApiException("DeliveryRecord cannot be null");
        }

        if (record.getContract() == null || record.getContract().getId() == null) {
            throw new ApiException("Contract is required");
        }

        Contract contract = contractRepository.findById(record.getContract().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        record.setContract(contract);
        // deliveryDate validation already handled in entity

        return deliveryRecordRepository.save(record);
    }

    // ✅ Retrieve specific record
    @Override
    public DeliveryRecord getRecordById(Long id) {

        if (id == null) {
            throw new ApiException("DeliveryRecord ID cannot be null");
        }

        return deliveryRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("DeliveryRecord not found with id: " + id));
    }

    // ✅ Retrieve all records for a contract
    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {

        if (contractId == null) {
            throw new ApiException("Contract ID cannot be null");
        }

        if (!contractRepository.existsById(contractId)) {
            throw new ResourceNotFoundException("Contract not found with id: " + contractId);
        }

        return deliveryRecordRepository.findByContractId(contractId);
    }

    // ✅ Get most recent delivery record
    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {

        List<DeliveryRecord> records = getDeliveryRecordsForContract(contractId);

        return records.stream()
                .max(Comparator.comparing(DeliveryRecord::getDeliveryDate))
                .orElseThrow(() ->
                        new ResourceNotFoundException("No delivery records found for contract"));
    }
}
