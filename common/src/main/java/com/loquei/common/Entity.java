package com.loquei.common;

import static java.util.Objects.requireNonNull;

import com.loquei.common.validation.ValidationHandler;
import java.util.Objects;

public abstract class Entity<ID extends Identifier> {

    protected final ID id;

    protected Entity(final ID anId) {
        this.id = requireNonNull(anId);
    }

    public abstract void validate(ValidationHandler aHandler);

    public ID getId() {
        return this.id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Entity<?> entity = (Entity<?>) o;
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
