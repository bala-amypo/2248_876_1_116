package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-reports")
@Tag(name = "Breach Report Management", description = "APIs for managing breach reports")
public class BreachReportController {

    @Autowired
    private BreachReportService breachReportService;

    @PostMapping("/generate/{contractId}")
    @Operation(summary = "Generate breach report for contract")
    public ResponseEntity<BreachReport> generateReport(@PathVariable Long contractId) {
        BreachReport report = breachReportService.generateReport(contractId);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    @Operation(summary = "Get all breach reports")
    public ResponseEntity<List<BreachReport>> getAllReports() {
        List<BreachReport> reports = breachReportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/contract/{contractId}")
    @Operation(summary = "Get breach reports for contract")
    public ResponseEntity<List<BreachReport>> getReportsForContract(@PathVariable Long contractId) {
        List<BreachReport> reports = breachReportService.getReportsForContract(contractId);
        return ResponseEntity.ok(reports);
    }
}