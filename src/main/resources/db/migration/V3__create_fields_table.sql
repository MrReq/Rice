CREATE TABLE fields
(
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    area DOUBLE PRECISION NOT NULL,

    soil_type VARCHAR(255) NOT NULL,

    latitude DOUBLE PRECISION,

    longitude DOUBLE PRECISION,

    farm_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_field_farm
        FOREIGN KEY (farm_id)
            REFERENCES farms(id)
            ON DELETE CASCADE
);