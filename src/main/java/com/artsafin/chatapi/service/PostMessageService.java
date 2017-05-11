package com.artsafin.chatapi.service;

import com.artsafin.chatapi.domain.agent.AgentNotFoundException;
import com.artsafin.chatapi.domain.chat.ChatNotFoundException;
import com.artsafin.chatapi.domain.chat.ChatPersister;
import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.domain.client.UnsupportedClientException;
import com.artsafin.chatapi.domain.event.Event;
import com.artsafin.chatapi.domain.event.impl.MessageFromAgent;
import com.artsafin.chatapi.domain.event.impl.MessageFromVisitor;
import com.artsafin.chatapi.domain.repository.EventRepository;
import com.artsafin.chatapi.domain.visitor.VisitorNotFoundException;

import java.time.Instant;

public class PostMessageService {
    private final ChatPersister persister;
    private final EventRepository eventRepository;

    public PostMessageService(ChatPersister persister, EventRepository eventRepository) {
        this.persister = persister;
        this.eventRepository = eventRepository;
    }

    private void post(ChatSession.Id chatId, Event msg) throws ChatNotAvailableException {
        try {
            eventRepository.add(chatId, msg);
            ChatSession chatSession = persister.load(chatId);
            chatSession.publishEvent(msg);
        } catch (ChatNotFoundException|VisitorNotFoundException|UnsupportedClientException|AgentNotFoundException e) {
            throw new ChatNotAvailableException(e);
        }
    }

    public void visitorMessage(ChatSession.Id chatId, Instant ts, String message) throws ChatNotAvailableException {
        post(chatId, new MessageFromVisitor(ts, message));
    }

    public void agentMessage(ChatSession.Id chatId, Instant ts, String message) throws ChatNotAvailableException {
        post(chatId, new MessageFromAgent(ts, message));
    }
}
