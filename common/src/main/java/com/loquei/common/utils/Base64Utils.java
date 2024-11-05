package com.loquei.common.utils;

import java.util.Base64;

public final class Base64Utils {

    private Base64Utils() {}

    public static String encode(final byte[] value) {
        return Base64.getEncoder().encodeToString(value);
    }

    public static byte[] decode(final String value) {
        return Base64.getDecoder().decode(value);
    }
}
