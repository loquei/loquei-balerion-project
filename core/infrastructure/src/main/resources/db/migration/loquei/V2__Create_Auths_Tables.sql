CREATE TABLE auths
(
    id         VARCHAR(255)                   NOT NULL,
    email      VARCHAR(255)                   NOT NULL,
    auth_code  VARCHAR(255)                   NOT NULL,
    auth_token VARCHAR(255)                   NOT NULL,
    expires_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_auths PRIMARY KEY (id)
);