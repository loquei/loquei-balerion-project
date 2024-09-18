CREATE TABLE IF NOT EXISTS users
(
    id            VARCHAR(255)                   NOT NULL,
    username      VARCHAR(255)                   NOT NULL,
    personal_name VARCHAR(255)                   NOT NULL,
    email         VARCHAR(255)                   NOT NULL,
    phone         VARCHAR(255)                   NOT NULL,
    document      VARCHAR(255)                   NOT NULL,
    birth         DATE                           NOT NULL,
    created_at    TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    updated_at    TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);
