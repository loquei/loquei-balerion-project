package com.loquei.common.event;

public interface EventListener<T extends Event> {
    void onEvent(T event);
}
