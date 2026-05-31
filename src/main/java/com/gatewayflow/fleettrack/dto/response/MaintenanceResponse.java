package com.gatewayflow.fleettrack.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MaintenanceResponse(

        Long id,

        String serviceType,

        LocalDate serviceDate,

        LocalDate nextServiceDate,

        String notes,

        Long vehicleId,

        String vehiclePlateNumber

) {
}