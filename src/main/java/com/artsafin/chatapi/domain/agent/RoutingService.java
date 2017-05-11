package com.artsafin.chatapi.domain.agent;

import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.domain.client.ClientFactory;
import com.artsafin.chatapi.domain.client.UnsupportedClientException;
import com.artsafin.chatapi.domain.client.impl.AfkClient;
import com.artsafin.chatapi.domain.repository.AgentRepository;
import com.artsafin.chatapi.domain.visitor.Visitor;

public class RoutingService {
    private final AgentRepository agentRepository;
    private final ClientFactory clientFactory;

    public RoutingService(AgentRepository agentRepository, ClientFactory clientFactory) {
        this.agentRepository = agentRepository;
        this.clientFactory = clientFactory;
    }

    public Agent findAgent(Visitor visitor, ChatSession chatSession) throws UnsupportedClientException {
        /*
            * Online +1000
            * Had chats with visitor +500
            * Language match +100
            * Language EN +50
         */
        PersistentAgent pa = agentRepository.firstFromAvailabilityRating(visitor);
        if (pa == null) {
            return new AfkAgent(new AfkClient(chatSession));
        }
        return Agent.fromPersistent(pa, clientFactory);
    }
}
