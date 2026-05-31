package com.gatewayflow.fleettrack.specification;

import com.gatewayflow.fleettrack.domain.entity.Vehicle;
import com.gatewayflow.fleettrack.domain.enums.VehicleStatus;
import org.springframework.data.jpa.domain.Specification;

public final class VehicleSpecification {

    private VehicleSpecification() {
    }

    public static Specification<Vehicle> hasStatus(VehicleStatus status) {

        return (root, query, cb) ->
                status == null
                        ? null
                        : cb.equal(
                        root.get("status"),
                        status
                );
    }

    public static Specification<Vehicle> hasYear(Integer year) {

        return (root, query, cb) ->
                year == null
                        ? null
                        : cb.equal(
                        root.get("year"),
                        year
                );
    }

    public static Specification<Vehicle> hasDriver(Long driverId) {

        return (root, query, cb) ->
                driverId == null
                        ? null
                        : cb.equal(
                        root.get("driver")
                                .get("id"),
                        driverId
                );
    }
}
