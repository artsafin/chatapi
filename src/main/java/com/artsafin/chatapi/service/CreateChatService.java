package com.artsafin.chatapi.service;

import com.artsafin.chatapi.domain.chat.ChatPersister;
import com.artsafin.chatapi.domain.client.Client;
import com.artsafin.chatapi.domain.client.UnsupportedClientException;
import com.artsafin.chatapi.domain.event.impl.MessageFromAgent;
import com.artsafin.chatapi.domain.agent.Agent;
import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.domain.visitor.Visitor;
import com.artsafin.chatapi.domain.event.impl.MessageFromVisitor;
import com.artsafin.chatapi.domain.visitor.VisitInfo;
import com.artsafin.chatapi.domain.agent.RoutingService;
import com.artsafin.chatapi.domain.visitor.VisitorService;

public class CreateChatService {

    private final VisitorService visitorService;
    private final RoutingService routing;
    private final ChatPersister chatPersister;

    public CreateChatService(VisitorService visitorService, RoutingService routing, ChatPersister chatPersister) {
        this.visitorService = visitorService;
        this.routing = routing;
        this.chatPersister = chatPersister;
    }

    public void createChat(VisitInfo visitInfo) throws UnsupportedClientException {
        Visitor visitor = visitorService.getOrCreateVisitor(visitInfo);
        Client client = visitorService.createVisitorClient(visitInfo);

        ChatSession chat = ChatSession.newChat(visitInfo, visitor, client);
        Agent agent = routing.findAgent(visitor, chat);
        chat.assignAgent(agent);
        chatPersister.persist(chat);

        if (visitInfo.hasInitialMessage()) {
            chat.publishEvent(new MessageFromVisitor(visitInfo.initialMessage));
        } else if (agent.hasInitialMessage()) {
            chat.publishEvent(new MessageFromAgent(agent.getInitialMessage()));
        }

        chat.resume();
    }
}
