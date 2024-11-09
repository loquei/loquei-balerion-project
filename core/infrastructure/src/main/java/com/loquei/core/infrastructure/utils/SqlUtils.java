package com.loquei.core.infrastructure.utils;

import java.util.stream.Collectors;

public final class SqlUtils {

    private SqlUtils() {}

    public static String upper(final String term) {
        if (term == null) return null;
        return term.toUpperCase();
    }

    public static String like(final String term) {
        if (term == null) return null;
        return "%" + term + "%";
    }

    public static String dynamicLike(final String term) {
        if (term == null) return null;
        return "%" + term.chars()
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .collect(Collectors.joining("%")) + "%";
    }

}
