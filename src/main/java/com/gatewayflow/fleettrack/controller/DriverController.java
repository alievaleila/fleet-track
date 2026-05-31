package com.gatewayflow.fleettrack.controller;

import com.gatewayflow.fleettrack.dto.request.DriverRequest;
import com.gatewayflow.fleettrack.dto.response.DriverResponse;
import com.gatewayflow.fleettrack.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverResponse> create(
            @Valid @RequestBody DriverRequest request
    ) {
        return ResponseEntity.ok(
                driverService.create(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                driverService.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<Page<DriverResponse>> getAll(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy
    ) {
        return ResponseEntity.ok(
                driverService.getAll(
                        page,
                        size,
                        sortBy
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody DriverRequest request
    ) {
        return ResponseEntity.ok(
                driverService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        driverService.delete(id);

        return ResponseEntity.noContent().build();
    }
}