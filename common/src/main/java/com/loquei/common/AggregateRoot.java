package com.loquei.common;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID anId) {
        super(anId);
    }
}
