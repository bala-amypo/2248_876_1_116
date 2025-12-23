package com.example.demo.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Contract;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract createContract(Contract contract) {
        if (contract.getContractValue().intValue() <= 0) {
            throw new ApiException("contract value must be positive");
        }

        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new ApiException("contract not found"));

        contract.setId(id); // ✅ now works
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ApiException("contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ApiException("contract not found"));

        contract.setActive(!contract.isActive());
        contractRepository.save(contract);
    }
}
