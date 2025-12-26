package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@Tag(name = "Application", description = "Application status and information")
public class HomeController {

    @GetMapping
    @Operation(summary = "Application home page")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Contract Management System");
        response.put("status", "Running");
        response.put("version", "1.0.0");
        return response;
    }

    @GetMapping("/health")
    @Operation(summary = "Health check endpoint")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", "Contract Management System");
        return response;
    }
}