CREATE TABLE recently_viewed_items
(
    id         VARCHAR(255)                   NOT NULL,
    user_id    VARCHAR(255)                   NOT NULL,
    item_id    VARCHAR(255)                   NOT NULL,
    viewed_at  TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_recently_viewed_items PRIMARY KEY (id)
);