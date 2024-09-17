CREATE TABLE security_users
(
    id         VARCHAR(255)                   NOT NULL,
    username   VARCHAR(50)                    NOT NULL,
    email      VARCHAR(255)                   NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_security_users PRIMARY KEY (id)
);

ALTER TABLE security_users
    ADD CONSTRAINT uc_security_users_email UNIQUE (email);

ALTER TABLE security_users
    ADD CONSTRAINT uc_security_users_username UNIQUE (username);