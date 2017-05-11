package com.artsafin.chatapi.domain.event;

import com.artsafin.chatapi.util.LookupCriteriaBuilder;
import com.artsafin.chatapi.util.LookupSpec;
import com.artsafin.chatapi.domain.chat.ChatSession;

import static com.artsafin.chatapi.util.LookupCriteriaBuilder.Operation.*;

public class EventSpec implements LookupSpec<Event> {
    public static final String CHAT_ID = "chat_id";
    public static final String DIR = "dir";

    private final ChatSession.Id chatId;
    private final Event.Direction direction;

    public EventSpec(ChatSession.Id chatId, Event.Direction direction) {
        this.chatId = chatId;
        this.direction = direction;
    }

    @Override
    public void buildCriterias(LookupCriteriaBuilder cb) {
        cb.and(CHAT_ID, EQUALS, chatId.toString());
        cb.and(DIR, EQUALS, direction.toString());
    }
}
