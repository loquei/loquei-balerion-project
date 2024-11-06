package com.loquei.core.infrastructure.event;

import com.loquei.common.event.Event;
import com.loquei.common.event.EventDispatcher;
import com.loquei.common.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AsyncEventDispatcher implements EventDispatcher {

    private final ConcurrentHashMap<Class<? extends Event>, CopyOnWriteArrayList<EventListener<? extends Event>>> listeners = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    @Override
    public <T extends Event> void dispatch(final T event) {
        CopyOnWriteArrayList<EventListener<? extends Event>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (EventListener<? extends Event> listener : eventListeners) {
                executorService.submit(() -> invokeListener(listener, event));
            }
        }
    }

    @Override
    public <T extends Event> void registerListener(final Class<T> eventType, final EventListener<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(listener);
    }

    private <T extends Event> void invokeListener(final EventListener<? extends Event> listener, final T event) {
        @SuppressWarnings("unchecked")
        final var typedListener = (EventListener<T>) listener;
        typedListener.onEvent(event);
    }

}
