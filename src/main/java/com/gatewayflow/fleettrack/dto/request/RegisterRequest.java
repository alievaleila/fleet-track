package com.gatewayflow.fleettrack.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String password;
    private String role;
}