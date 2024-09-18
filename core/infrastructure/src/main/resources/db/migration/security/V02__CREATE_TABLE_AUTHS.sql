CREATE TABLE security_authentications
(
    id         VARCHAR(255)                   NOT NULL,
    email      VARCHAR(255)                   NOT NULL,
    auth_code  VARCHAR(6)                     NOT NULL,
    auth_token VARCHAR(255)                   NOT NULL,
    expires_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_security_authentications PRIMARY KEY (id)
);

-- ALTER TABLE security_authentications
--     ADD CONSTRAINT uc_security_authentications_email UNIQUE (email);