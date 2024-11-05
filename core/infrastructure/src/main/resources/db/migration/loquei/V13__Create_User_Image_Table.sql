CREATE TABLE user_image
(
    id         VARCHAR(255)                   NOT NULL,
    user_id    VARCHAR(255),
    file_name  VARCHAR(255),
    file_type  VARCHAR(255),
    data       VARCHAR(255),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_user_image PRIMARY KEY (id)
);