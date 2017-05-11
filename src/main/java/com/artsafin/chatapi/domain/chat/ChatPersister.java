package com.artsafin.chatapi.domain.chat;

import com.artsafin.chatapi.domain.agent.AgentNotFoundException;
import com.artsafin.chatapi.domain.client.ClientFactory;
import com.artsafin.chatapi.domain.client.UnsupportedClientException;
import com.artsafin.chatapi.domain.repository.AgentRepository;
import com.artsafin.chatapi.domain.repository.ChatSessionRepository;
import com.artsafin.chatapi.domain.repository.VisitorRepository;
import com.artsafin.chatapi.domain.visitor.VisitorNotFoundException;

public class ChatPersister {
    private final ChatSessionRepository chatSessionRepository;
    private final VisitorRepository visitorRepository;
    private final AgentRepository agentRepository;
    private final ClientFactory clientFactory;

    public ChatPersister(ChatSessionRepository chatSessionRepository,
                         VisitorRepository visitorRepository,
                         AgentRepository agentRepository,
                         ClientFactory clientFactory) {
        this.chatSessionRepository = chatSessionRepository;
        this.visitorRepository = visitorRepository;
        this.agentRepository = agentRepository;
        this.clientFactory = clientFactory;
    }

    public void persist(ChatSession chatSession) {
        chatSessionRepository.add(chatSession);
        visitorRepository.add(chatSession.getVisitor());
        agentRepository.add(chatSession.getAgent());
    }

    public ChatSession load(ChatSession.Id id) throws ChatNotFoundException, VisitorNotFoundException, UnsupportedClientException, AgentNotFoundException {
        PersistentChatSession pchat = chatSessionRepository.findById(id);

        return ChatSession.fromPersistent(pchat, visitorRepository, clientFactory, agentRepository);
    }
}
