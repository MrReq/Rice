CREATE TABLE farms
(
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,
    country VARCHAR(255),
    region VARCHAR(255),
    area DOUBLE PRECISION,

    owner_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_farm_owner
        FOREIGN KEY (owner_id)
            REFERENCES users(id)
);