package com.artsafin.chatapi.domain.repository;

import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.util.LookupSpec;
import com.artsafin.chatapi.domain.event.Event;

import java.util.ArrayList;

public interface EventRepository {
    ArrayList<Event> findBy(LookupSpec<Event> eventSpec);

    void add(ChatSession.Id chatId, Event msg);
}
