CREATE TABLE images
(
    size       BIGINT                         NOT NULL,
    type       VARCHAR(255)                   NOT NULL,
    content    OID                            NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    bucket     VARCHAR(255)                   NOT NULL,
    key        VARCHAR(255)                   NOT NULL,
    CONSTRAINT pk_images PRIMARY KEY (bucket, key)
);