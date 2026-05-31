package com.gatewayflow.fleettrack.service.impl;

import com.gatewayflow.fleettrack.domain.entity.Driver;
import com.gatewayflow.fleettrack.domain.entity.Vehicle;
import com.gatewayflow.fleettrack.dto.request.VehicleFilterRequest;
import com.gatewayflow.fleettrack.dto.request.VehicleRequest;
import com.gatewayflow.fleettrack.dto.response.VehicleResponse;
import com.gatewayflow.fleettrack.exception.BadRequestException;
import com.gatewayflow.fleettrack.exception.ResourceNotFoundException;
import com.gatewayflow.fleettrack.mapper.VehicleMapper;
import com.gatewayflow.fleettrack.repository.DriverRepository;
import com.gatewayflow.fleettrack.repository.VehicleRepository;
import com.gatewayflow.fleettrack.service.VehicleService;
import com.gatewayflow.fleettrack.specification.VehicleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    @Transactional
    public VehicleResponse create(VehicleRequest request) {

        if (vehicleRepository.existsByPlateNumber(request.plateNumber())) {
            throw new BadRequestException(
                    "Vehicle with this plate number already exists"
            );
        }

        Driver driver = null;

        if (request.driverId() != null) {
            driver = driverRepository.findById(request.driverId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Driver not found"));
        }

        Vehicle vehicle = vehicleMapper.toEntity(request);
        vehicle.setDriver(driver);

        return vehicleMapper.toResponse(
                vehicleRepository.save(vehicle)
        );
    }

    @Override
    @Cacheable(value = "vehicles", key = "#id")
    public VehicleResponse getById(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with id: " + id
                        ));

        return vehicleMapper.toResponse(vehicle);
    }

    @Override
    public Page<VehicleResponse> getAll(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable =
                PageRequest.of(page, size, Sort.by(sortBy));

        return vehicleRepository.findAll(pageable)
                .map(vehicleMapper::toResponse);
    }

    @Override
    @Transactional
    @CacheEvict(value = "vehicles", key = "#id")
    public VehicleResponse update(Long id, VehicleRequest request) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found"
                        ));

        vehicle.setMake(request.make());
        vehicle.setModel(request.model());
        vehicle.setYear(request.year());
        vehicle.setPlateNumber(request.plateNumber());
        vehicle.setStatus(request.status());

        if (request.driverId() != null) {

            Driver driver = driverRepository.findById(
                            request.driverId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Driver not found"
                            ));

            vehicle.setDriver(driver);
        }

        return vehicleMapper.toResponse(
                vehicleRepository.save(vehicle)
        );
    }

    @Override
    @Transactional
    @CacheEvict(value = "vehicles", key = "#id")
    public void delete(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found"
                        ));

        vehicleRepository.delete(vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleResponse> search(VehicleFilterRequest filter, int page, int size, String sortBy) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy)
                );

        Specification<Vehicle> specification =
                Specification.where(
                                VehicleSpecification
                                        .hasStatus(
                                                filter.status()
                                        )
                        )
                        .and(
                                VehicleSpecification
                                        .hasYear(
                                                filter.year()
                                        )
                        )
                        .and(
                                VehicleSpecification
                                        .hasDriver(
                                                filter.driverId()
                                        )
                        );

        return vehicleRepository
                .findAll(specification, pageable)
                .map(vehicleMapper::toResponse);
    }
}