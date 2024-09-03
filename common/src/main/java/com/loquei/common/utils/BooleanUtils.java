package com.loquei.common.utils;

public final class BooleanUtils {

    private BooleanUtils() {}

    public static boolean isTrue(final Boolean value) {
        return Boolean.TRUE.equals(value);
    }

    public static boolean isFalse(final Boolean value) {
        return Boolean.FALSE.equals(value);
    }
}
