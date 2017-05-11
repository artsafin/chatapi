package com.artsafin.chatapi.domain.client;

import com.artsafin.chatapi.domain.client.impl.DebugClient;
import com.artsafin.chatapi.domain.client.impl.HttpClient;
import com.artsafin.chatapi.domain.client.impl.TelegramClient;

public class ClientFactory {
    public Client create(Client.Type t) throws UnsupportedClientException {
        if (t == Client.Type.DEBUG) {
            return new DebugClient();
        }
        if (t == Client.Type.TELEGRAM) {
            return new TelegramClient();
        }
        if (t == Client.Type.SIMPLE_HTTP) {
            return new HttpClient();
        }

        throw new UnsupportedClientException();
    }
    public Client create(String type) throws UnsupportedClientException {
        return create(Client.Type.valueOf(type));
    }
}
