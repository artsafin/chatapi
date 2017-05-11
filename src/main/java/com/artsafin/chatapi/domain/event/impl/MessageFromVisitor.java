package com.artsafin.chatapi.domain.event.impl;

import com.artsafin.chatapi.domain.event.Event;

import java.time.Instant;

public class MessageFromVisitor extends Event {
    private final String message;

    public MessageFromVisitor(Instant ts, String message) {
        super(ts, Direction.IN);
        this.message = message;
    }

    public MessageFromVisitor(String s) {
        this(Instant.now(), s);
    }

    public String getMessage() {
        return message;
    }
}
