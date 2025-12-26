package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalty-calculations")
@Tag(name = "Penalty Calculation", description = "APIs for penalty calculations")
public class PenaltyCalculationController {

    @Autowired
    private PenaltyCalculationService penaltyCalculationService;

    @PostMapping("/calculate/{contractId}")
    @Operation(summary = "Calculate penalty for contract")
    public ResponseEntity<PenaltyCalculation> calculatePenalty(@PathVariable Long contractId) {
        PenaltyCalculation calculation = penaltyCalculationService.calculatePenalty(contractId);
        return ResponseEntity.ok(calculation);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get penalty calculation by ID")
    public ResponseEntity<PenaltyCalculation> getCalculation(@PathVariable Long id) {
        PenaltyCalculation calculation = penaltyCalculationService.getCalculationById(id);
        return ResponseEntity.ok(calculation);
    }

    @GetMapping("/contract/{contractId}")
    @Operation(summary = "Get penalty calculations for contract")
    public ResponseEntity<List<PenaltyCalculation>> getCalculationsForContract(@PathVariable Long contractId) {
        List<PenaltyCalculation> calculations = penaltyCalculationService.getCalculationsForContract(contractId);
        return ResponseEntity.ok(calculations);
    }
}