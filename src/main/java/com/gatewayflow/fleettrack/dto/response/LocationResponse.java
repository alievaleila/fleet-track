package com.gatewayflow.fleettrack.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LocationResponse(

        Long vehicleId,

        Double latitude,

        Double longitude,

        LocalDateTime trackedAt

) {
}