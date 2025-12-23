package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contract;

public interface ContractService {

    Contract createContract(Contract contract);

    Contract updateContract(Long id, Contract contract);

    Contract getContractById(Long id);

    List<Contract> getAllContracts();

    void updateContractStatus(Long id);
}
