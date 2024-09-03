package com.loquei.core.domain.security.auth;

import com.loquei.common.ValueObject;
import com.loquei.common.utils.RandomCodeUtils;

import java.util.Objects;

public class SecurityAuthCode extends ValueObject {

    private final String code;

    private SecurityAuthCode(final String code) {
        this.code = code;
    }

    public static SecurityAuthCode generate() {
        final var randomCodeSize = 6;
        final var randomCode = RandomCodeUtils.generateRandomCode(randomCodeSize);
        return SecurityAuthCode.from(randomCode);
    }

    public static SecurityAuthCode from(final String code) {
        return new SecurityAuthCode(code);
    }

    public String getValue() {
        return code;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final SecurityAuthCode that = (SecurityAuthCode) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
