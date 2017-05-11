package com.artsafin.chatapi.domain.client.impl;

import com.artsafin.chatapi.domain.client.AbstractClient;
import com.artsafin.chatapi.domain.event.Event;

public class HttpClient extends AbstractClient {
    public HttpClient() {
    }

    @Override
    public void onEvent(Event event) {
        if (!isAllowed(event)) {
            return;
        }
    }
}
