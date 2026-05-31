package com.gatewayflow.fleettrack.repository;

import com.gatewayflow.fleettrack.domain.entity.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceRepository extends JpaRepository<MaintenanceLog, Long> {

    List<MaintenanceLog> findByVehicleId(Long vehicleId);

    List<MaintenanceLog> findByNextServiceDateBefore(LocalDate date);

    List<MaintenanceLog> findByNextServiceDateLessThanEqual(LocalDate date);
}