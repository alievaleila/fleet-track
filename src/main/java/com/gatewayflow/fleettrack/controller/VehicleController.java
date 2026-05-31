package com.gatewayflow.fleettrack.controller;

import com.gatewayflow.fleettrack.domain.enums.VehicleStatus;
import com.gatewayflow.fleettrack.dto.request.VehicleFilterRequest;
import com.gatewayflow.fleettrack.dto.request.VehicleRequest;
import com.gatewayflow.fleettrack.dto.response.VehicleResponse;
import com.gatewayflow.fleettrack.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Vehicle Management",
        description = "Vehicle CRUD operations"
)
@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(
            summary = "Create vehicle",
            description = "Creates a new vehicle"
    )
    @PostMapping
    public ResponseEntity<VehicleResponse> create(
            @Valid @RequestBody VehicleRequest request
    ) {
        return ResponseEntity.ok(
                vehicleService.create(request)
        );
    }

    @Operation(
            summary = "Get vehicle by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                vehicleService.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<Page<VehicleResponse>> getAll(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy

    ) {

        return ResponseEntity.ok(
                vehicleService.getAll(page, size, sortBy)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> update(
            @PathVariable Long id,
            @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<VehicleResponse>>
    search(

            @RequestParam(required = false)
            VehicleStatus status,

            @RequestParam(required = false)
            Integer year,

            @RequestParam(required = false)
            Long driverId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy

    ) {

        VehicleFilterRequest filter =
                new VehicleFilterRequest(
                        status,
                        year,
                        driverId
                );

        return ResponseEntity.ok(
                vehicleService.search(
                        filter,
                        page,
                        size,
                        sortBy
                )
        );
    }
}