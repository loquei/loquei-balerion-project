package com.loquei.core.domain.category;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;
import java.util.Objects;

public class CategoryId extends Identifier {
    private final String value;

    private CategoryId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CategoryId unique() {
        return CategoryId.from(IdUtils.uuid());
    }

    public static CategoryId from(final String anId) {
        return new CategoryId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final CategoryId that = (CategoryId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
