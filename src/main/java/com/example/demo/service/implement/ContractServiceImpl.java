package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.exception.*;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract createContract(Contract contract) {

        if (contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Base contract value invalid");
        }

        if (contractRepository.findByContractNumber(contract.getContractNumber()).isPresent()) {
            throw new BadRequestException("Contract already exists");
        }

        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
}
