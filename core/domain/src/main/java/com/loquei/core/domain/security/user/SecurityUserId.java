package com.loquei.core.domain.security.user;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class SecurityUserId extends Identifier {

    private final String value;

    private SecurityUserId(String value) {
        this.value = requireNonNull(value);
    }

    public static SecurityUserId unique() {
        return SecurityUserId.from(IdUtils.uuid());
    }

    public static SecurityUserId from(final String anId) {
        return new SecurityUserId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final SecurityUserId that = (SecurityUserId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
