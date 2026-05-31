package com.gatewayflow.fleettrack.service.impl;

import com.gatewayflow.fleettrack.domain.entity.User;
import com.gatewayflow.fleettrack.domain.enums.Role;
import com.gatewayflow.fleettrack.dto.request.LoginRequest;
import com.gatewayflow.fleettrack.dto.request.RegisterRequest;
import com.gatewayflow.fleettrack.dto.response.AuthResponse;
import com.gatewayflow.fleettrack.exception.BadRequestException;
import com.gatewayflow.fleettrack.exception.ResourceNotFoundException;
import com.gatewayflow.fleettrack.repository.UserRepository;
import com.gatewayflow.fleettrack.security.JwtService;
import com.gatewayflow.fleettrack.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(
                        Role.valueOf(
                                request.getRole().toUpperCase()
                        )
                )
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());

        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUsername());

        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}