package com.loquei.common.exceptions;

import com.loquei.common.AggregateRoot;
import com.loquei.common.Entity;
import com.loquei.common.Identifier;
import com.loquei.common.validation.Error;
import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String aMessage, final List<Error> someErrors) {
        super(aMessage, someErrors);
    }

    public static NotFoundException with(final Class<? extends AggregateRoot<?>> anAggregate, final Identifier id) {
        final var anError = "%s with id %s was not found"
                .formatted(anAggregate.getSimpleName().toLowerCase(), id.getValue());
        return new NotFoundException(anError, Collections.emptyList());
    }

    public static NotFoundException with(final Class<? extends AggregateRoot<?>> anAggregate, final String email) {
        final var anError = "%s with email %s was not found"
                .formatted(anAggregate.getSimpleName().toLowerCase(), email);
        return new NotFoundException(anError, Collections.emptyList());
    }

    public static NotFoundException with(final Identifier id, final Class<? extends Entity<?>> anAggregate) {
        final var anError = "%s with id %s was not found"
                .formatted(anAggregate.getSimpleName().toLowerCase(), id.getValue());
        return new NotFoundException(anError, Collections.emptyList());
    }
}
