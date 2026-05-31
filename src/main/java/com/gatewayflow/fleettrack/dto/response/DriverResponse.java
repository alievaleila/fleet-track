package com.gatewayflow.fleettrack.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DriverResponse(

        Long id,

        String firstName,

        String lastName,

        String email,

        String phone,

        String licenseNumber,

        LocalDate licenseExpiryDate

) {
}