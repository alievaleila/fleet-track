package com.gatewayflow.fleettrack.service.impl;

import com.gatewayflow.fleettrack.domain.entity.Vehicle;
import com.gatewayflow.fleettrack.domain.entity.VehicleLocation;
import com.gatewayflow.fleettrack.dto.request.LocationRequest;
import com.gatewayflow.fleettrack.dto.response.LocationResponse;
import com.gatewayflow.fleettrack.exception.ResourceNotFoundException;
import com.gatewayflow.fleettrack.mapper.VehicleLocationMapper;
import com.gatewayflow.fleettrack.repository.VehicleLocationRepository;
import com.gatewayflow.fleettrack.repository.VehicleRepository;
import com.gatewayflow.fleettrack.service.VehicleLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleLocationServiceImpl implements VehicleLocationService {

    private final VehicleRepository vehicleRepository;
    private final VehicleLocationRepository locationRepository;
    private final VehicleLocationMapper mapper;

    @Override
    public LocationResponse updateLocation(LocationRequest request) {

        Vehicle vehicle =
                vehicleRepository.findById(
                                request.vehicleId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Vehicle not found"
                                ));

        VehicleLocation location =
                VehicleLocation.builder()
                        .latitude(
                                request.latitude())
                        .longitude(
                                request.longitude())
                        .trackedAt(
                                LocalDateTime.now())
                        .vehicle(vehicle)
                        .build();

        return mapper.toResponse(
                locationRepository.save(location)
        );
    }
}