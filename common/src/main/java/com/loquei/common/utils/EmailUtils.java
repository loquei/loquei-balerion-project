package com.loquei.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class EmailUtils {

    private static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";
    private static final String MONEY_FORMAT = "#,##0.00";

    private EmailUtils() {}

    public static String formatLocalDateTime(final LocalDateTime value) {
        return value.format(ofPattern(DD_MM_YYYY_FORMAT));
    }

    public static String formatBigDecimal(final BigDecimal value) {
        return new DecimalFormat(MONEY_FORMAT).format(value);
    }

}
