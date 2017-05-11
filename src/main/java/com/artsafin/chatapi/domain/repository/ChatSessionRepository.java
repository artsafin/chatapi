package com.artsafin.chatapi.domain.repository;

import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.domain.chat.PersistentChatSession;

public interface ChatSessionRepository {
    void add(ChatSession chatSession);

    PersistentChatSession findById(ChatSession.Id id);
}
