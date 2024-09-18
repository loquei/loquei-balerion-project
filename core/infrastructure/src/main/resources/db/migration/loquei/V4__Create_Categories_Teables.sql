CREATE TABLE categories
(
    id          VARCHAR(255)                   NOT NULL,
    name        VARCHAR(255)                   NOT NULL,
    description VARCHAR(4000),
    active      BOOLEAN                        NOT NULL,
    created_at  TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    deleted_at  TIMESTAMP(6) WITHOUT TIME ZONE,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);