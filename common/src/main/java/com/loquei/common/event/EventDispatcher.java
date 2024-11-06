package com.loquei.common.event;

public interface EventDispatcher {
    <T extends Event> void dispatch(T event);
    <T extends Event> void registerListener(Class<T> eventType, EventListener<T> listener);
}
