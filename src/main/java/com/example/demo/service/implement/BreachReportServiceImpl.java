package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {
    
    private BreachReportRepository breachReportRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;
    
    @Override
    public BreachReport generateReport(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
            .orElseThrow(() -> new RuntimeException("Contract not found"));
        
        PenaltyCalculation calculation = penaltyCalculationRepository
            .findTopByContractIdOrderByCalculatedAtDesc(contractId)
            .orElseThrow(() -> new RuntimeException("No penalty calculation found for contract"));
        
        BreachReport report = BreachReport.builder()
            .contract(contract)
            .daysDelayed(calculation.getDaysDelayed())
            .penaltyAmount(calculation.getCalculatedPenalty())
            .build();
        
        return breachReportRepository.save(report);
    }
    
    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }
    
    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}