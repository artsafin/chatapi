package com.artsafin.chatapi.domain.agent;

import com.artsafin.chatapi.domain.client.ClientFactory;
import com.artsafin.chatapi.domain.client.UnsupportedClientException;
import com.artsafin.chatapi.util.AbstractId;
import com.artsafin.chatapi.domain.client.Client;

public class Agent extends PersistentAgent {
    public static class Id extends AbstractId {
        public Id(String s) {
            super(s);
        }
    }

    public enum Status {
        ONLINE,
        OFFLINE
    }

    private Client client;

    public static Agent fromPersistent(PersistentAgent pa, ClientFactory clientFactory) throws UnsupportedClientException {
        return new Agent(pa.getId(), pa.getName(), pa.getStatus(), clientFactory.create(Client.Type.SIMPLE_HTTP));
    }

    public Agent(Id id, String name, Status status, Client client) {
        super(id, name, status);
        this.client = client;
    }

    public String getInitialMessage() {
        return "hello from agent";
    }

    public boolean hasInitialMessage() {
        return true;
    }

    public Client getClient() {
        return client;
    }
}
