package com.artsafin.chatapi.service;

import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.domain.event.Event;
import com.artsafin.chatapi.domain.event.EventSpec;
import com.artsafin.chatapi.domain.repository.EventRepository;

import java.util.ArrayList;

public class EventsService {
    private final EventRepository repository;

    public EventsService(EventRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Event> getEvents(ChatSession.Id chatId, Event.Direction direction) {
        return repository.findBy(new EventSpec(chatId, direction));
    }
}
