CREATE TABLE maintenance_logs
(
    id BIGSERIAL PRIMARY KEY,

    service_type VARCHAR(100) NOT NULL,

    service_date DATE NOT NULL,

    next_service_date DATE,

    notes TEXT,

    vehicle_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_maintenance_vehicle
        FOREIGN KEY (vehicle_id)
            REFERENCES vehicles(id)
);