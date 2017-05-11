package com.artsafin.chatapi.domain.repository;

import com.artsafin.chatapi.domain.agent.Agent;
import com.artsafin.chatapi.domain.agent.AgentNotFoundException;
import com.artsafin.chatapi.domain.agent.PersistentAgent;
import com.artsafin.chatapi.domain.visitor.PersistentVisitor;

public interface AgentRepository {
    void add(Agent agent);

    PersistentAgent requireById(Agent.Id agentId) throws AgentNotFoundException;

    PersistentAgent firstFromAvailabilityRating(PersistentVisitor visitor);
}
