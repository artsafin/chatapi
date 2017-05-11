package com.artsafin.chatapi.domain.visitor;

import com.artsafin.chatapi.domain.chat.PersistentChatSession;
import com.artsafin.chatapi.domain.visitor.contact.PersistentContact;

import java.util.ArrayList;
import java.util.HashSet;

public class PersistentVisitor {
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String CLIENT_ID = "client_id";
    public static final String LANGUAGE = "language";
    public static final String COUNTRY = "country";

    private final Visitor.Id id;
    private final String name;
    private final String country;
    private final String language;
    private final HashSet<PersistentContact> contacts = new HashSet<>();
    private final ArrayList<PersistentChatSession> chats = new ArrayList<>();

    public PersistentVisitor(Visitor.Id id, String name, String country, String language) {
        this(id, name, country, language, null, null);
    }

    public PersistentVisitor(Visitor.Id id, String name, String country, String language, HashSet<? extends PersistentContact> contacts, ArrayList<? extends PersistentChatSession> chats) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.language = language;

        if (contacts != null) {
            this.contacts.addAll(contacts);
        }
        if (chats != null) {
            this.chats.addAll(chats);
        }
    }

    public Visitor.Id getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public HashSet<PersistentContact> getContacts() {
        return contacts;
    }

    public ArrayList<PersistentChatSession> getChats() {
        return chats;
    }
}
