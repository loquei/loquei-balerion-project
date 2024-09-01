package com.loquei.common;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}
