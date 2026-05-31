package com.gatewayflow.fleettrack.mapper;

import com.gatewayflow.fleettrack.domain.entity.MaintenanceLog;
import com.gatewayflow.fleettrack.dto.request.MaintenanceRequest;
import com.gatewayflow.fleettrack.dto.response.MaintenanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    MaintenanceLog toEntity(MaintenanceRequest request);

    @Mapping(source = "vehicle.id", target = "vehicleId")
    @Mapping(source = "vehicle.plateNumber", target = "vehiclePlateNumber")
    MaintenanceResponse toResponse(MaintenanceLog maintenanceLog);
}
