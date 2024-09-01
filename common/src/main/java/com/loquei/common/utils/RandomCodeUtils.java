package com.loquei.common.utils;

import java.security.SecureRandom;

public final class RandomCodeUtils {

    private static final SecureRandom secureRandom = new SecureRandom();

    private RandomCodeUtils() {}

    public static String generateRandomCode(final Integer size) {
        final int min = (int) Math.pow(10, size - 1);
        final int max = (int) Math.pow(10, size) - 1;
        return String.valueOf(secureRandom.nextInt((max - min) + 1) + min);
    }
}
