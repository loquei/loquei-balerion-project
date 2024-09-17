package com.loquei.core.infrastructure.item.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemCategoryId implements Serializable {

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(name = "category_id", nullable = false)
    private String categoryId;

    public ItemCategoryId() {}

    private ItemCategoryId(final String itemId, final String categoryId) {
        this.itemId = itemId;
        this.categoryId = categoryId;
    }

    public static ItemCategoryId from(final String itemId, final String categoryId) {
        return new ItemCategoryId(itemId, categoryId);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ItemCategoryId that = (ItemCategoryId) o;
        return Objects.equals(getItemId(), that.getItemId()) && Objects.equals(getCategoryId(), that.getCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getCategoryId());
    }
}
