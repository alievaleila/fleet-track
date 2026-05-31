package com.gatewayflow.fleettrack.service;

import com.gatewayflow.fleettrack.dto.request.MaintenanceRequest;
import com.gatewayflow.fleettrack.dto.response.MaintenanceResponse;

import java.util.List;

public interface MaintenanceService {

    MaintenanceResponse create(MaintenanceRequest request);

    MaintenanceResponse getById(Long id);

    List<MaintenanceResponse> getByVehicle(Long vehicleId);

    void delete(Long id);
}