CREATE TABLE drivers
(
    id BIGSERIAL PRIMARY KEY,

    first_name VARCHAR(50) NOT NULL,

    last_name VARCHAR(50) NOT NULL,

    email VARCHAR(100),

    phone VARCHAR(30),

    license_number VARCHAR(50) NOT NULL UNIQUE,

    license_expiry_date DATE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL
);