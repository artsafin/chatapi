package com.artsafin.chatapi.domain.agent;

import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.domain.client.Client;
import com.artsafin.chatapi.domain.client.impl.AfkClient;

public class AfkAgent extends Agent {
    public AfkAgent(Client client) {
        super(new Agent.Id("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee"), "All away agent", Status.ONLINE, client);
    }
}
