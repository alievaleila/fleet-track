package com.gatewayflow.fleettrack.controller;

import com.gatewayflow.fleettrack.dto.request.LoginRequest;
import com.gatewayflow.fleettrack.dto.request.RegisterRequest;
import com.gatewayflow.fleettrack.dto.response.AuthResponse;
import com.gatewayflow.fleettrack.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}