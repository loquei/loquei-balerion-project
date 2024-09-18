CREATE TABLE addresses
(
    id           VARCHAR(255)                   NOT NULL,
    user_id      VARCHAR(255)                   NOT NULL,
    postal_code  VARCHAR(255)                   NOT NULL,
    street       VARCHAR(255)                   NOT NULL,
    neighborhood VARCHAR(255)                   NOT NULL,
    city         VARCHAR(255)                   NOT NULL,
    state        VARCHAR(255)                   NOT NULL,
    country      VARCHAR(255)                   NOT NULL,
    number       BIGINT                         NOT NULL,
    main         BOOLEAN                        NOT NULL,
    created_at   TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    updated_at   TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_addresses PRIMARY KEY (id)
);