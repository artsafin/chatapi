package com.artsafin.chatapi.domain.visitor.contact;

import java.util.HashSet;
import java.util.stream.Collectors;

public class Contact extends PersistentContact {
    public Contact(String id, Type type) {
        super(id, type);
    }

    public static HashSet<Contact> fromPersistentSet(HashSet<PersistentContact> contacts) {
        return new HashSet<>(contacts.stream().map(pc -> new Contact(pc.getId(), pc.getType())).collect(Collectors.toSet()));
    }

    public enum Type {
        PHONE,
        EMAIL,
        TELEGRAM,
        ADDRESS
    }
}
