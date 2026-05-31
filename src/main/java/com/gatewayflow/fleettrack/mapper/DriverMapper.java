package com.gatewayflow.fleettrack.mapper;

import com.gatewayflow.fleettrack.domain.entity.Driver;
import com.gatewayflow.fleettrack.dto.request.DriverRequest;
import com.gatewayflow.fleettrack.dto.response.DriverResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver toEntity(DriverRequest request);

    DriverResponse toResponse(Driver driver);
}