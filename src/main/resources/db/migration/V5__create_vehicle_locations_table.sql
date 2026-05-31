CREATE TABLE vehicle_locations
(
    id BIGSERIAL PRIMARY KEY,

    latitude DOUBLE PRECISION NOT NULL,

    longitude DOUBLE PRECISION NOT NULL,

    tracked_at TIMESTAMP NOT NULL,

    vehicle_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_location_vehicle
        FOREIGN KEY (vehicle_id)
            REFERENCES vehicles(id)
);