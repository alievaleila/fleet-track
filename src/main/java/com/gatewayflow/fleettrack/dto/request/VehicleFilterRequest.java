package com.gatewayflow.fleettrack.dto.request;


import com.gatewayflow.fleettrack.domain.enums.VehicleStatus;

public record VehicleFilterRequest(

        VehicleStatus status,

        Integer year,

        Long driverId

) {
}