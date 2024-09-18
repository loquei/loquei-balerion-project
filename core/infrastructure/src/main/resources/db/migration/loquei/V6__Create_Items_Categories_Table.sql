CREATE TABLE items_categories
(
    item_id     VARCHAR(255) NOT NULL,
    category_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_items_categories PRIMARY KEY (item_id, category_id)
);

ALTER TABLE items_categories
    ADD CONSTRAINT FK_ITEMS_CATEGORIES_ON_ITEM FOREIGN KEY (item_id) REFERENCES items (id);