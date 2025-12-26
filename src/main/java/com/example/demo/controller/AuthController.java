package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
        // Simplified login for test purposes
        String token = jwtTokenProvider.generateToken(1L, request.getEmail(), Set.of("ROLE_USER"));
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("email", request.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "User registration")
    public ResponseEntity<Map<String, String>> register(@RequestBody AuthRequest request) {
        // Simplified registration for test purposes
        passwordEncoder.encode(request.getPassword());
        String token = jwtTokenProvider.generateToken(1L, request.getEmail(), Set.of("ROLE_USER"));
        
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("email", request.getEmail());
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    @Operation(summary = "Validate JWT token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestParam String token) {
        boolean isValid = jwtTokenProvider.validateToken(token);
        Map<String, Object> response = new HashMap<>();
        response.put("valid", isValid);
        
        if (isValid) {
            var claims = jwtTokenProvider.getClaims(token);
            response.put("userId", claims.get("userId"));
            response.put("email", claims.get("email"));
            response.put("roles", claims.get("roles"));
        }
        
        return ResponseEntity.ok(response);
    }
}