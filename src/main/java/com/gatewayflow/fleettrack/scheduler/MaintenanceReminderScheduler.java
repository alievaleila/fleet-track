package com.gatewayflow.fleettrack.scheduler;

import com.gatewayflow.fleettrack.constants.RedisChannels;
import com.gatewayflow.fleettrack.domain.entity.MaintenanceLog;
import com.gatewayflow.fleettrack.dto.request.MaintenanceNotification;
import com.gatewayflow.fleettrack.redis.NotificationPublisher;
import com.gatewayflow.fleettrack.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MaintenanceReminderScheduler {

    private final MaintenanceRepository maintenanceRepository;
    private final NotificationPublisher publisher;

    @Scheduled(cron = "0 0 8 * * *")
    public void checkUpcomingMaintenance() {

        LocalDate today = LocalDate.now();

        List<MaintenanceLog> records =
                maintenanceRepository
                        .findByNextServiceDateLessThanEqual(
                                today.plusDays(7)
                        );

        records.forEach(record -> {

            MaintenanceNotification notification =
                    MaintenanceNotification.builder()
                            .vehicleId(
                                    record.getVehicle().getId()
                            )
                            .plateNumber(
                                    record.getVehicle()
                                            .getPlateNumber()
                            )
                            .message(
                                    "Maintenance due within 7 days"
                            )
                            .build();

            publisher.publish(
                    RedisChannels.MAINTENANCE_ALERTS,
                    notification
            );
        });
    }
}