CREATE TABLE item_image
(
    id         VARCHAR(255)                   NOT NULL,
    item_id    VARCHAR(255),
    file_name  VARCHAR(255),
    file_type  VARCHAR(255),
    data       OID,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_item_image PRIMARY KEY (id)
);