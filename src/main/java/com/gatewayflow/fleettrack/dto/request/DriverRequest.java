package com.gatewayflow.fleettrack.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DriverRequest(

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Email(message = "Invalid email")
        String email,

        String phone,

        @NotBlank(message = "License number is required")
        String licenseNumber,

        LocalDate licenseExpiryDate

) {
}