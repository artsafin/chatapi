package com.artsafin.chatapi.domain.client.impl;

import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.domain.client.AbstractClient;
import com.artsafin.chatapi.domain.event.Event;
import com.artsafin.chatapi.domain.event.impl.MessageFromAgent;
import com.artsafin.chatapi.domain.event.impl.MessageFromVisitor;

public class AfkClient extends AbstractClient {
    private final ChatSession chatSession;

    public AfkClient(ChatSession chatSession) {

        this.chatSession = chatSession;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageFromVisitor) {
            chatSession.publishEvent(new MessageFromAgent("Nobody is available at the moment because fuck you that's why"));
        }
    }
}
