package com.loquei.common.utils;

public final class TimeUtils {

    private TimeUtils() {}

    public static long minutesToMillis(final long minutes) {
        return minutes * 60 * 1000;
    }

    public static long hoursToMillis(final long hours) {
        return hours * 60 * 60 * 1000;
    }
}
