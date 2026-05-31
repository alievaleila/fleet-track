package com.gatewayflow.fleettrack.service;

import com.gatewayflow.fleettrack.dto.request.VehicleFilterRequest;
import com.gatewayflow.fleettrack.dto.request.VehicleRequest;
import com.gatewayflow.fleettrack.dto.response.VehicleResponse;
import org.springframework.data.domain.Page;

public interface VehicleService {

    VehicleResponse create(VehicleRequest request);

    VehicleResponse getById(Long id);

    Page<VehicleResponse> getAll(int page, int size, String sortBy);

    VehicleResponse update(Long id, VehicleRequest request);

    void delete(Long id);

    Page<VehicleResponse> search(VehicleFilterRequest filter, int page, int size, String sortBy);
}