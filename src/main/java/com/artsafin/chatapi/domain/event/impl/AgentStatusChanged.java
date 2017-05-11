package com.artsafin.chatapi.domain.event.impl;

import com.artsafin.chatapi.domain.agent.Agent;
import com.artsafin.chatapi.domain.event.Event;

import java.time.Instant;

public class AgentStatusChanged extends Event {
    private final String agentName;
    private final Agent.Status oldStatus;
    private final Agent.Status newStatus;

    public AgentStatusChanged(Agent agent, Agent.Status oldStatus, Agent.Status newStatus) {
        super(Instant.now(), Direction.OUT, Importance.HIGH);

        this.agentName = agent.getName();
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public String getAgentName() {
        return agentName;
    }

    public Agent.Status getOldStatus() {
        return oldStatus;
    }

    public Agent.Status getNewStatus() {
        return newStatus;
    }
}
