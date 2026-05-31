CREATE TABLE vehicles
(
    id BIGSERIAL PRIMARY KEY,

    make VARCHAR(100) NOT NULL,

    model VARCHAR(100) NOT NULL,

    year INTEGER,

    plate_number VARCHAR(20) UNIQUE NOT NULL,

    status VARCHAR(30) NOT NULL,

    driver_id BIGINT,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_vehicle_driver
        FOREIGN KEY (driver_id)
            REFERENCES drivers(id)
);