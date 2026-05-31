package com.gatewayflow.fleettrack.repository;

import com.gatewayflow.fleettrack.domain.entity.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleLocationRepository extends JpaRepository<VehicleLocation, Long> {

    List<VehicleLocation> findTop10ByVehicleIdOrderByTrackedAtDesc(Long vehicleId);
}