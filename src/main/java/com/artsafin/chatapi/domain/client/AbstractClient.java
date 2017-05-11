package com.artsafin.chatapi.domain.client;

import com.artsafin.chatapi.domain.event.Event;

import java.util.Arrays;
import java.util.HashSet;

public abstract class AbstractClient implements Client {
    private final HashSet<Class> allowed = new HashSet<>();

    @Override
    public void allowEvents(Class... classes) {
        allowed.addAll(Arrays.asList(classes));
    }

    public boolean isAllowed(Event event) {
        return allowed.contains(event.getClass());
    }
}
