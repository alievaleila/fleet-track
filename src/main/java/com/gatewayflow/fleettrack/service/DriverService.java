package com.gatewayflow.fleettrack.service;

import com.gatewayflow.fleettrack.dto.request.DriverRequest;
import com.gatewayflow.fleettrack.dto.response.DriverResponse;
import org.springframework.data.domain.Page;

public interface DriverService {

    DriverResponse create(DriverRequest request);

    DriverResponse getById(Long id);

    Page<DriverResponse> getAll(int page, int size, String sortBy);

    DriverResponse update(Long id, DriverRequest request);

    void delete(Long id);
}