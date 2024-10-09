CREATE TABLE rentals
(
    id                  VARCHAR(255)                   NOT NULL,
    lessor_id           VARCHAR(255)                   NOT NULL,
    lessee_id           VARCHAR(255)                   NOT NULL,
    item_id             VARCHAR(255)                   NOT NULL,
    start_date          TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    end_date            TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    total_value         DECIMAL(19, 2)                 NOT NULL,
    status              VARCHAR(50)                    NOT NULL,
    cancellation_reason TEXT,
    created_at          TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    updated_at          TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_rent PRIMARY KEY (id),
    CONSTRAINT fk_rent_lessor FOREIGN KEY (lessor_id) REFERENCES users(id),
    CONSTRAINT fk_rent_lessee FOREIGN KEY (lessee_id) REFERENCES users(id),
    CONSTRAINT fk_rent_item FOREIGN KEY (item_id) REFERENCES items(id)
);