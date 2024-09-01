package com.loquei.common.exceptions;

public class NoStacktraceException extends RuntimeException {

    public NoStacktraceException(final String aMessage) {
        this(aMessage, null);
    }

    public NoStacktraceException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause, true, false);
    }
}
