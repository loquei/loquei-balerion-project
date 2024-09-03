package com.loquei.core.domain.user;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class UserId extends Identifier {

    private final String value;

    private UserId(String value) {
        this.value = requireNonNull(value);
    }

    public static UserId unique() {
        return UserId.from(IdUtils.uuid());
    }

    public static UserId from(final String anId) {
        return new UserId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final UserId that = (UserId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
