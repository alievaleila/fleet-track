package com.gatewayflow.fleettrack.service.impl;

import com.gatewayflow.fleettrack.constants.CacheConstants;
import com.gatewayflow.fleettrack.domain.entity.MaintenanceLog;
import com.gatewayflow.fleettrack.domain.entity.Vehicle;
import com.gatewayflow.fleettrack.dto.request.MaintenanceRequest;
import com.gatewayflow.fleettrack.dto.response.MaintenanceResponse;
import com.gatewayflow.fleettrack.exception.ResourceNotFoundException;
import com.gatewayflow.fleettrack.mapper.MaintenanceMapper;
import com.gatewayflow.fleettrack.repository.MaintenanceRepository;
import com.gatewayflow.fleettrack.repository.VehicleRepository;
import com.gatewayflow.fleettrack.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MaintenanceServiceImpl
        implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Override
    public MaintenanceResponse create(
            MaintenanceRequest request
    ) {

        Vehicle vehicle =
                vehicleRepository.findById(
                                request.vehicleId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Vehicle not found"
                                ));

        MaintenanceLog maintenance =
                maintenanceMapper.toEntity(request);

        maintenance.setVehicle(vehicle);

        return maintenanceMapper.toResponse(
                maintenanceRepository.save(
                        maintenance
                )
        );
    }

    @Override
    @Cacheable(
            value = CacheConstants.MAINTENANCE,
            key = "#id"
    )
    @Transactional(readOnly = true)
    public MaintenanceResponse getById(Long id) {

        MaintenanceLog maintenance =
                maintenanceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Maintenance record not found"
                                ));

        return maintenanceMapper.toResponse(
                maintenance
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceResponse> getByVehicle(
            Long vehicleId
    ) {

        return maintenanceRepository
                .findByVehicleId(vehicleId)
                .stream()
                .map(maintenanceMapper::toResponse)
                .toList();
    }

    @Override
    @CacheEvict(
            value = CacheConstants.MAINTENANCE,
            key = "#id"
    )
    public void delete(Long id) {

        MaintenanceLog maintenance =
                maintenanceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Maintenance record not found"
                                ));

        maintenanceRepository.delete(
                maintenance
        );
    }
}
