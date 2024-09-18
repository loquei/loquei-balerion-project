package com.loquei.core.domain.rating;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;
import java.util.Objects;

public class RatingId extends Identifier {
    private final String value;

    private RatingId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static RatingId unique() {
        return RatingId.from(IdUtils.uuid());
    }

    public static RatingId from(final String anId) {
        return new RatingId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final RatingId that = (RatingId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
