package com.gatewayflow.fleettrack.service.impl;

import com.gatewayflow.fleettrack.constants.CacheConstants;
import com.gatewayflow.fleettrack.domain.entity.Driver;
import com.gatewayflow.fleettrack.dto.request.DriverRequest;
import com.gatewayflow.fleettrack.dto.response.DriverResponse;
import com.gatewayflow.fleettrack.exception.BadRequestException;
import com.gatewayflow.fleettrack.exception.ResourceNotFoundException;
import com.gatewayflow.fleettrack.mapper.DriverMapper;
import com.gatewayflow.fleettrack.repository.DriverRepository;
import com.gatewayflow.fleettrack.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public DriverResponse create(DriverRequest request) {

        if (driverRepository.existsByLicenseNumber(
                request.licenseNumber())) {

            throw new BadRequestException(
                    "License number already exists"
            );
        }

        Driver driver =
                driverMapper.toEntity(request);

        return driverMapper.toResponse(
                driverRepository.save(driver)
        );
    }

    @Override
    @Cacheable(
            value = CacheConstants.DRIVERS,
            key = "#id"
    )
    @Transactional(readOnly = true)
    public DriverResponse getById(Long id) {

        Driver driver =
                driverRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Driver not found"
                                ));

        return driverMapper.toResponse(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DriverResponse> getAll(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy)
                );

        return driverRepository.findAll(pageable)
                .map(driverMapper::toResponse);
    }

    @Override
    @CacheEvict(
            value = CacheConstants.DRIVERS,
            key = "#id"
    )
    public DriverResponse update(
            Long id,
            DriverRequest request
    ) {

        Driver driver =
                driverRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Driver not found"
                                ));

        driver.setFirstName(request.firstName());
        driver.setLastName(request.lastName());
        driver.setEmail(request.email());
        driver.setPhone(request.phone());
        driver.setLicenseNumber(
                request.licenseNumber()
        );
        driver.setLicenseExpiryDate(
                request.licenseExpiryDate()
        );

        return driverMapper.toResponse(
                driverRepository.save(driver)
        );
    }

    @Override
    @CacheEvict(
            value = CacheConstants.DRIVERS,
            key = "#id"
    )
    public void delete(Long id) {

        Driver driver =
                driverRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Driver not found"
                                ));

        driverRepository.delete(driver);
    }
}