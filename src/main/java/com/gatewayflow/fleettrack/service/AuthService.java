package com.gatewayflow.fleettrack.service;

import com.gatewayflow.fleettrack.dto.request.LoginRequest;
import com.gatewayflow.fleettrack.dto.request.RegisterRequest;
import com.gatewayflow.fleettrack.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
