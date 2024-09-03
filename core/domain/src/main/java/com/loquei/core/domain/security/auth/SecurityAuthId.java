package com.loquei.core.domain.security.auth;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class SecurityAuthId extends Identifier {

    private final String value;

    private SecurityAuthId(String value) {
        this.value = requireNonNull(value);
    }

    public static SecurityAuthId unique() {
        return SecurityAuthId.from(IdUtils.uuid());
    }

    public static SecurityAuthId from(final String anId) {
        return new SecurityAuthId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final SecurityAuthId that = (SecurityAuthId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
