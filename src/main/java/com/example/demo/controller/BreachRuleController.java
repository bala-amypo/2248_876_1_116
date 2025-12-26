package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
@Tag(name = "Breach Rule Management", description = "APIs for managing breach rules")
public class BreachRuleController {

    @Autowired
    private BreachRuleService breachRuleService;

    @PostMapping
    @Operation(summary = "Create a new breach rule")
    public ResponseEntity<BreachRule> createRule(@RequestBody BreachRule rule) {
        BreachRule created = breachRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    @Operation(summary = "Get all breach rules")
    public ResponseEntity<List<BreachRule>> getAllRules() {
        List<BreachRule> rules = breachRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a breach rule")
    public ResponseEntity<Void> deactivateRule(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active-default")
    @Operation(summary = "Get active default breach rule")
    public ResponseEntity<BreachRule> getActiveDefaultRule() {
        BreachRule rule = breachRuleService.getActiveDefaultOrFirst();
        return ResponseEntity.ok(rule);
    }
}