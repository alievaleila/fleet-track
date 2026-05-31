package com.gatewayflow.fleettrack.mapper;

import com.gatewayflow.fleettrack.dto.request.VehicleRequest;
import com.gatewayflow.fleettrack.dto.response.VehicleResponse;
import com.gatewayflow.fleettrack.domain.entity.Vehicle;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toEntity(VehicleRequest request);

    @Mapping(source = "driver.firstName", target = "driverName")
    VehicleResponse toResponse(Vehicle vehicle);
}