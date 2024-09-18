CREATE TABLE items
(
    id          VARCHAR(255)                   NOT NULL,
    name        VARCHAR(255)                   NOT NULL,
    description VARCHAR(255)                   NOT NULL,
    daily_value DECIMAL                        NOT NULL,
    max_days    INTEGER                        NOT NULL,
    min_days    INTEGER                        NOT NULL,
    created_at  TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_items PRIMARY KEY (id)
);