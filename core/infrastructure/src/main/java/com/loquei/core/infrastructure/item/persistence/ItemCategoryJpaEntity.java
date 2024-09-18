package com.loquei.core.infrastructure.item.persistence;

import com.loquei.core.domain.category.CategoryId;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items_categories")
public class ItemCategoryJpaEntity {

    @EmbeddedId
    private ItemCategoryId id;

    @ManyToOne
    @MapsId("itemId")
    private ItemJpaEntity item;

    public ItemCategoryJpaEntity() {}

    private ItemCategoryJpaEntity(final CategoryId categoryId, final ItemJpaEntity item) {
        this.id = ItemCategoryId.from(item.getId(), categoryId.getValue());
        this.item = item;
    }

    public static ItemCategoryJpaEntity from(final CategoryId id, final ItemJpaEntity item) {
        return new ItemCategoryJpaEntity(id, item);
    }

    public ItemCategoryId getId() {
        return id;
    }

    public void setId(ItemCategoryId id) {
        this.id = id;
    }

    public ItemJpaEntity getItem() {
        return item;
    }

    public void setItem(ItemJpaEntity item) {
        this.item = item;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ItemCategoryJpaEntity that = (ItemCategoryJpaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getItem());
    }
}
