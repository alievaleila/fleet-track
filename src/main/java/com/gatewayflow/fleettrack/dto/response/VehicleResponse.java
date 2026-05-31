package com.gatewayflow.fleettrack.dto.response;

import com.gatewayflow.fleettrack.domain.enums.VehicleStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponse {

    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String plateNumber;
    private VehicleStatus status;
    private String driverName;
}