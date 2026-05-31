package com.gatewayflow.fleettrack.controller;

import com.gatewayflow.fleettrack.dto.request.MaintenanceRequest;
import com.gatewayflow.fleettrack.dto.response.MaintenanceResponse;
import com.gatewayflow.fleettrack.service.MaintenanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<MaintenanceResponse> create(
            @Valid
            @RequestBody
            MaintenanceRequest request
    ) {

        return ResponseEntity.ok(
                maintenanceService.create(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                maintenanceService.getById(id)
        );
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<MaintenanceResponse>>
    getByVehicle(
            @PathVariable Long vehicleId
    ) {

        return ResponseEntity.ok(
                maintenanceService.getByVehicle(
                        vehicleId
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        maintenanceService.delete(id);

        return ResponseEntity.noContent()
                .build();
    }
}