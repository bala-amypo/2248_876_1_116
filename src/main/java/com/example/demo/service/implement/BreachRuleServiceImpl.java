package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {
    
    private BreachRuleRepository breachRuleRepository;
    
    @Override
    public BreachRule createRule(BreachRule rule) {
        if (rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Penalty per day must be greater than zero");
        }
        if (rule.getMaxPenaltyPercentage() > 100.0) {
            throw new IllegalArgumentException("Max penalty percentage cannot exceed 100%");
        }
        return breachRuleRepository.save(rule);
    }
    
    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rule not found with id: " + id));
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }
    
    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
            .orElseThrow(() -> new RuntimeException("No active breach rule found"));
    }
    
    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}