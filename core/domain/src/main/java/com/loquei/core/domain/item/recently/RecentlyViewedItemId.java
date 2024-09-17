package com.loquei.core.domain.item.recently;

import static java.util.Objects.requireNonNull;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;
import java.util.Objects;

public class RecentlyViewedItemId extends Identifier {

    private final String value;

    private RecentlyViewedItemId(String value) {
        this.value = requireNonNull(value);
    }

    public static RecentlyViewedItemId unique() {
        return RecentlyViewedItemId.from(IdUtils.uuid());
    }

    public static RecentlyViewedItemId from(final String anId) {
        return new RecentlyViewedItemId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final RecentlyViewedItemId that = (RecentlyViewedItemId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
