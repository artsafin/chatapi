package com.artsafin.chatapi.domain.agent;

public class PersistentAgent {
    private Agent.Id id;
    private String name;
    private Agent.Status status;

    public PersistentAgent(Agent.Id id, String name, Agent.Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public static PersistentAgent fromAgent(Agent agent) {
        return new PersistentAgent(agent.getId(), agent.getName(), agent.getStatus());
    }

    public Agent.Id getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Agent.Status getStatus() {
        return status;
    }
}
