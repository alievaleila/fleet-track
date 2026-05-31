package com.gatewayflow.fleettrack.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MaintenanceRequest(

        @NotBlank(message = "Service type is required")
        String serviceType,

        @NotNull(message = "Service date is required")
        LocalDate serviceDate,

        LocalDate nextServiceDate,

        String notes,

        @NotNull(message = "Vehicle id is required")
        Long vehicleId

) {
}