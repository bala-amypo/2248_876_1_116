package com.example.demo.service.implement;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Contract;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class ContractServiceImpl {

    public Contract createContract(Contract contract) {

        if (contract == null) {
            throw new ApiException("Contract cannot be null");
        }

        if (contract.getContractNumber() == null || contract.getContractNumber().isEmpty()) {
            throw new ApiException("Contract number is required");
        }

        if (contract.getBaseContractValue() == null || contract.getBaseContractValue() <= 0) {
            throw new ApiException("Base contract value must be greater than zero");
        }

        return contract;
    }

    public Contract getContractById(Long id) {

        if (id == null) {
            throw new ApiException("Contract ID cannot be null");
        }

        throw new ResourceNotFoundException("Contract not found with id: " + id);
    }
}
