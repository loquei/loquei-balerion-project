package com.loquei.core.domain.item;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ItemId extends Identifier {
    private final String value;

    private ItemId(String value) {
        this.value = requireNonNull(value);
    }

    public static ItemId unique() {
        return ItemId.from(IdUtils.uuid());
    }

    public static ItemId from(final String anId) {
        return new ItemId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final ItemId that = (ItemId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
