package com.artsafin.chatapi.domain.event;

import java.util.ArrayList;

public class EventBus {
    private boolean isResumed = false;
    private final ArrayList<EventListener> listeners = new ArrayList<>();
    private final ArrayList<Event> eventQueue = new ArrayList<>();

    private void sendOne(Event event) {
        for (EventListener l : listeners) {
            l.onEvent(event);
        }
    }

    public void publish(Event event) {
        if (isResumed) {
            sendOne(event);
        } else {
            eventQueue.add(event);
        }
    }

    public void resume() {
        isResumed = true;
        for (Event e: eventQueue) {
            sendOne(e);
        }
        eventQueue.clear();
    }

    public void attach(EventListener listener) {
        listeners.add(listener);
    }
}
