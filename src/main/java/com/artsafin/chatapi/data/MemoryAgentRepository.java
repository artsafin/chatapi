package com.artsafin.chatapi.data;

import com.artsafin.chatapi.domain.agent.Agent;
import com.artsafin.chatapi.domain.agent.AgentNotFoundException;
import com.artsafin.chatapi.domain.agent.PersistentAgent;
import com.artsafin.chatapi.domain.repository.AgentRepository;
import com.artsafin.chatapi.domain.visitor.PersistentVisitor;

import java.util.HashMap;

public class MemoryAgentRepository implements AgentRepository {
    private final HashMap<Agent.Id, PersistentAgent> data = new HashMap<>();

    {
        PersistentAgent[] agents = new PersistentAgent[]{
                new PersistentAgent(new Agent.Id("b1c03042-e69b-4616-b20a-934cc3eefe3d"), "Petr Petrovich", Agent.Status.ONLINE),
                new PersistentAgent(new Agent.Id("be9e9002-f9a6-44a2-8a21-5d1d78360a5f"), "Ivan Ivanovich", Agent.Status.OFFLINE)
        };
        for (PersistentAgent pa: agents) {
            data.put(pa.getId(), pa);
        }
    }

    @Override
    public void add(Agent agent) {
        data.put(agent.getId(), agent);
    }

    @Override
    public PersistentAgent requireById(Agent.Id agentId) throws AgentNotFoundException {
        if (!data.containsKey(agentId)) {
            throw new AgentNotFoundException(agentId);
        }
        return data.get(agentId);
    }

    @Override
    public PersistentAgent firstFromAvailabilityRating(PersistentVisitor visitor) {
        return null;
    }
}
