package com.loquei.common.exceptions;

public class InternalErrorException extends NoStacktraceException {

    protected InternalErrorException(final String aMessage, final Throwable t) {
        super(aMessage, t);
    }

    public static InternalErrorException with(final String aMessage, final Throwable t) {
        return new InternalErrorException(aMessage, t);
    }
}
