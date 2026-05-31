package com.gatewayflow.fleettrack.dto.request;

import com.gatewayflow.fleettrack.domain.enums.VehicleStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleRequest(

        @NotBlank(message = "Make is required")
        String make,

        @NotBlank(message = "Model is required")
        String model,

        @NotNull(message = "Year is required")
        @Min(1900)
        @Max(2100)
        Integer year,

        @NotBlank(message = "Plate number is required")
        String plateNumber,

        @NotNull(message = "Status is required")
        VehicleStatus status,

        Long driverId
) {
}