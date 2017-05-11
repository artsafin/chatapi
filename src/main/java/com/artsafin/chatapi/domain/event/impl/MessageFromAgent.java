package com.artsafin.chatapi.domain.event.impl;

import com.artsafin.chatapi.domain.event.Event;

import java.time.Instant;

public class MessageFromAgent extends Event {
    private final String message;

    public MessageFromAgent(Instant ts, String message) {
        super(ts, Direction.OUT);
        this.message = message;
    }

    public MessageFromAgent(String message) {
        this(Instant.now(), message);
    }

    public String getMessage() {
        return message;
    }
}
