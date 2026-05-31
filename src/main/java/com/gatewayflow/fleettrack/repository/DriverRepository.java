package com.gatewayflow.fleettrack.repository;

import com.gatewayflow.fleettrack.domain.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverRepository extends JpaRepository<Driver, Long> {

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsByEmail(String email);
}