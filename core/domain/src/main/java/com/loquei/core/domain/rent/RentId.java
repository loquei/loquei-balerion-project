package com.loquei.core.domain.rent;

import static java.util.Objects.requireNonNull;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;
import java.util.Objects;

public class RentId extends Identifier {

    private final String value;

    private RentId(String value) {
        this.value = requireNonNull(value);
    }

    public static RentId unique() {
        return RentId.from(IdUtils.uuid());
    }

    public static RentId from(final String anId) {
        return new RentId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final RentId that = (RentId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
