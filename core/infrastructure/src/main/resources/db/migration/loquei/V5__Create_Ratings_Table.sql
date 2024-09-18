CREATE TABLE ratings
(
    id          VARCHAR(255)                   NOT NULL,
    rater_id    VARCHAR(255)                   NOT NULL,
    description VARCHAR(4000),
    score       DOUBLE PRECISION,
    item_id     VARCHAR(255)                   NOT NULL,
    created_at  TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_ratings PRIMARY KEY (id)
);