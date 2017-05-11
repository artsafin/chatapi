package com.artsafin.chatapi.domain.client.impl;

import com.artsafin.chatapi.domain.client.AbstractClient;
import com.artsafin.chatapi.domain.event.Event;

import java.time.LocalDateTime;
import java.util.UUID;

public class DebugClient extends AbstractClient {
    private final UUID id = UUID.randomUUID();

    @Override
    public void onEvent(Event event) {
        System.out.printf("[%s] [%s] Event: %s", LocalDateTime.now(), id.toString(), event);
    }
}
