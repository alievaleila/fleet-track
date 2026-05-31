package com.gatewayflow.fleettrack.repository;

import com.gatewayflow.fleettrack.domain.entity.Vehicle;
import com.gatewayflow.fleettrack.domain.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {

    List<Vehicle> findByStatus(VehicleStatus status);

    boolean existsByPlateNumber(String plateNumber);
}