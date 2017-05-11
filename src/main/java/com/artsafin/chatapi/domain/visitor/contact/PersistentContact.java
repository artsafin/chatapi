package com.artsafin.chatapi.domain.visitor.contact;

public class PersistentContact {
    private final Contact.Type type;
    private final String id;

    public PersistentContact(String id, Contact.Type type) {
        this.id = id;
        this.type = type;
    }

    public Contact.Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
