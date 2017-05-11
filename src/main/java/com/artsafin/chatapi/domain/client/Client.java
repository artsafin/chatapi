package com.artsafin.chatapi.domain.client;

import com.artsafin.chatapi.domain.event.EventListener;

public interface Client extends EventListener {
    void allowEvents(Class... classes);

    enum Type {
        DEBUG,
        SIMPLE_HTTP,
        TELEGRAM,
        WECHAT,
        VK,
        FB;
    /*
        public static Type fromString(String str) throws UnsupportedClientException {
            if (str.equals("telegram")) {
                return TELEGRAM;
            }

            throw new UnsupportedClientException();
        }
        */
    }
}
