package com.loquei.core.domain.item.image;

import static java.util.Objects.requireNonNull;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;
import java.util.Objects;

public class ItemImageId extends Identifier {

    private final String value;

    private ItemImageId(String value) {
        this.value = requireNonNull(value);
    }

    public static ItemImageId unique() {
        return ItemImageId.from(IdUtils.uuid());
    }

    public static ItemImageId from(final String anId) {
        return new ItemImageId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final ItemImageId that = (ItemImageId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
