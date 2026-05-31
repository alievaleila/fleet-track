package com.gatewayflow.fleettrack.dto.request;

import lombok.Builder;

@Builder
public record MaintenanceNotification(

        Long vehicleId,

        String plateNumber,

        String message

) {
}