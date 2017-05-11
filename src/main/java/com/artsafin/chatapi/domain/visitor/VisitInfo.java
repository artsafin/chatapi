package com.artsafin.chatapi.domain.visitor;

public class VisitInfo {
    public final String name;
    public final String phone;
    public final String email;
    public final String country;
    public final String language;
    public final String clientType;
    public final String clientId;
    public final String initialMessage;

    public VisitInfo(String name, String phone, String email, String country, String language, String clientType, String clientId, String initialMessage) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.country = country;
        this.language = language;
        this.clientType = clientType;
        this.clientId = clientId;
        this.initialMessage = initialMessage;
    }

    public boolean hasInitialMessage() {
        return initialMessage != null;
    }

    public static VisitInfo fromVisitor(PersistentVisitor visitor, String clientType, String clientId) {
        return new VisitInfo(visitor.getName(), null, null, visitor.getCountry(), visitor.getLanguage(), clientType, clientId, null);
    }
}
