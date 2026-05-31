package com.gatewayflow.fleettrack.service;

import com.gatewayflow.fleettrack.dto.request.LocationRequest;
import com.gatewayflow.fleettrack.dto.response.LocationResponse;

public interface VehicleLocationService {

    LocationResponse updateLocation(LocationRequest request);
}