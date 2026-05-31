package com.gatewayflow.fleettrack.mapper;

import com.gatewayflow.fleettrack.domain.entity.VehicleLocation;
import com.gatewayflow.fleettrack.dto.response.LocationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleLocationMapper {

    LocationResponse toResponse(VehicleLocation location);
}