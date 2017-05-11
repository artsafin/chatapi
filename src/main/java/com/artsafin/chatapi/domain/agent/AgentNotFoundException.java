package com.artsafin.chatapi.domain.agent;

public class AgentNotFoundException extends Exception {
    private final Agent.Id agentId;

    public AgentNotFoundException(Agent.Id agentId) {
        super();
        this.agentId = agentId;
    }

    @Override
    public String toString() {
        return "AgentNotFoundException{" +
                "agentId=" + agentId +
                '}';
    }
}
