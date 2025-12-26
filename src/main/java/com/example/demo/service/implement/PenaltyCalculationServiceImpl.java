package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {
    
    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;
    private BreachRuleRepository breachRuleRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    
    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
            .orElseThrow(() -> new RuntimeException("Contract not found"));
        
        DeliveryRecord latestRecord = deliveryRecordRepository
            .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
            .orElseThrow(() -> new RuntimeException("No delivery record found for contract"));
        
        BreachRule rule = breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
            .orElseThrow(() -> new RuntimeException("No active breach rule found"));
        
        long daysDelayed = Math.max(0, ChronoUnit.DAYS.between(
            contract.getAgreedDeliveryDate(), latestRecord.getDeliveryDate()));
        
        BigDecimal penalty = rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed));
        BigDecimal maxPenalty = contract.getBaseContractValue()
            .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100.0));
        
        if (penalty.compareTo(maxPenalty) > 0) {
            penalty = maxPenalty;
        }
        
        PenaltyCalculation calculation = PenaltyCalculation.builder()
            .contract(contract)
            .daysDelayed((int) daysDelayed)
            .calculatedPenalty(penalty)
            .build();
        
        return penaltyCalculationRepository.save(calculation);
    }
    
    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Calculation not found with id: " + id));
    }
    
    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}