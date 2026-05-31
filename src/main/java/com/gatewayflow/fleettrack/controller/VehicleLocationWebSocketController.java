package com.gatewayflow.fleettrack.controller;


import com.gatewayflow.fleettrack.dto.request.LocationRequest;
import com.gatewayflow.fleettrack.dto.response.LocationResponse;
import com.gatewayflow.fleettrack.service.VehicleLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class VehicleLocationWebSocketController {

    private final VehicleLocationService service;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/location")
    public void updateLocation(LocationRequest request) {

        LocationResponse response =
                service.updateLocation(
                        request
                );

        messagingTemplate.convertAndSend(
                "/topic/locations",
                response
        );
    }
}