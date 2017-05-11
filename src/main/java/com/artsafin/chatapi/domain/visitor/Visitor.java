package com.artsafin.chatapi.domain.visitor;

import com.artsafin.chatapi.domain.chat.ChatSession;
import com.artsafin.chatapi.domain.chat.PersistentChatSession;
import com.artsafin.chatapi.domain.visitor.contact.Contact;
import com.artsafin.chatapi.domain.visitor.contact.PersistentContact;
import com.artsafin.chatapi.util.AbstractId;

import java.util.ArrayList;
import java.util.HashSet;

public class Visitor extends PersistentVisitor {
    public static Visitor fromPersistent(PersistentVisitor pv) {
        return new Visitor(pv.getId(), pv.getName(), pv.getCountry(), pv.getLanguage(), pv.getContacts(), pv.getChats());
    }

    public static class Id extends AbstractId {
        public Id() {
        }

        public Id(String s) {
            super(s);
        }
    }

    public Visitor(Id id, String name, String country, String language, HashSet<? extends PersistentContact> contacts, ArrayList<? extends PersistentChatSession> chats) {
        super(id, name, country, language, contacts, chats);
    }

    public void addChat(ChatSession chatSession) {
        getChats().add(chatSession);
    }

    public static Visitor newVisitor(VisitInfo info) {
        HashSet<Contact> contacts = new HashSet<>();
        if (info.email != null) {
            contacts.add(new Contact(info.email, Contact.Type.EMAIL));
        }
        if (info.phone != null) {
            contacts.add(new Contact(info.phone, Contact.Type.PHONE));
        }
        try {
            contacts.add(new Contact(info.clientId, Contact.Type.valueOf(info.clientType)));
        } catch (IllegalArgumentException ignored) {
        }

        return new Visitor(new Id(), info.name, info.country, info.language, contacts, null);
    }
}
