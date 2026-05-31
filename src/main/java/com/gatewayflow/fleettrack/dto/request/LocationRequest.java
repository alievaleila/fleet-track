package com.gatewayflow.fleettrack.dto.request;

import jakarta.validation.constraints.NotNull;

public record LocationRequest(

        @NotNull
        Double latitude,

        @NotNull
        Double longitude,

        @NotNull
        Long vehicleId

) {
}