package com.artsafin.chatapi.domain.chat;

import com.artsafin.chatapi.domain.agent.AgentNotFoundException;
import com.artsafin.chatapi.domain.client.ClientFactory;
import com.artsafin.chatapi.domain.client.UnsupportedClientException;
import com.artsafin.chatapi.domain.repository.AgentRepository;
import com.artsafin.chatapi.domain.repository.VisitorRepository;
import com.artsafin.chatapi.domain.visitor.PersistentVisitor;
import com.artsafin.chatapi.domain.visitor.VisitInfo;
import com.artsafin.chatapi.domain.visitor.VisitorNotFoundException;
import com.artsafin.chatapi.util.AbstractId;
import com.artsafin.chatapi.domain.client.Client;
import com.artsafin.chatapi.domain.event.Event;
import com.artsafin.chatapi.domain.event.EventBus;
import com.artsafin.chatapi.domain.agent.Agent;
import com.artsafin.chatapi.domain.visitor.Visitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChatSession extends PersistentChatSession {
    public static ChatSession fromPersistent(PersistentChatSession pc, VisitorRepository visitorRepository, ClientFactory clientFactory, AgentRepository agentRepository) throws VisitorNotFoundException, UnsupportedClientException, AgentNotFoundException {
        PersistentVisitor pv = visitorRepository.requireById(pc.visitorId);
        Visitor visitor = Visitor.fromPersistent(pv);
        Client client = clientFactory.create(pc.clientType);
        Agent agent = Agent.fromPersistent(agentRepository.requireById(pc.agentId), clientFactory);

        return new ChatSession(pc.id, VisitInfo.fromVisitor(pv, pc.clientType, pc.clientId), visitor, client, agent);
    }

    public static ChatSession newChat(@NotNull VisitInfo visitInfo, @NotNull Visitor visitor, @NotNull Client client) {
        return new ChatSession(new Id(), visitInfo, visitor, client, null);
    }

    public static class Id extends AbstractId {}

    private final Visitor visitor;
    private final EventBus eventBus;
    private Agent agent;

    public ChatSession(@NotNull ChatSession.Id id, @NotNull VisitInfo visitInfo, @NotNull Visitor visitor, @NotNull Client visitorClient, @Nullable Agent agent) {
        super(id, (agent != null) ? agent.getId() : null, visitor.getId(), visitInfo.clientType, visitInfo.clientId);
        this.visitor = visitor;
        this.agent = agent;

        visitor.addChat(this);

        eventBus = new EventBus();
        eventBus.attach(visitorClient);
        if (agent != null) {
            assignAgent(agent);
        }
    }

    public void assignAgent(@NotNull Agent agent) {
        this.agent = agent;
        eventBus.attach(agent.getClient());
    }

    public void publishEvent(@NotNull Event event) {
        eventBus.publish(event);
    }

    public void resume() {
        eventBus.resume();
    }

    public String getId() {
        return id.toString();
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public Agent getAgent() {
        return agent;
    }
}
