package com.artsafin.chatapi.domain.chat;

import com.artsafin.chatapi.domain.agent.Agent;
import com.artsafin.chatapi.domain.visitor.Visitor;

public class PersistentChatSession {
    protected final ChatSession.Id id;
    protected Agent.Id agentId;
    protected final Visitor.Id visitorId;
    protected final String clientType;
    protected final String clientId;

    public PersistentChatSession(ChatSession.Id id, Agent.Id agentId, Visitor.Id visitorId, String clientType, String clientId) {
        this.id = id;
        this.agentId = agentId;
        this.visitorId = visitorId;
        this.clientType = clientType;
        this.clientId = clientId;
    }
}
