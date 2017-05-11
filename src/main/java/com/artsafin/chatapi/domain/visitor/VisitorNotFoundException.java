package com.artsafin.chatapi.domain.visitor;

import com.artsafin.chatapi.util.LookupSpec;

public class VisitorNotFoundException extends Exception {
    private final Visitor.Id id;
    private final LookupSpec<Visitor> spec;

    public VisitorNotFoundException(Visitor.Id id) {
        this.id = id;
        this.spec = null;
    }

    public VisitorNotFoundException(LookupSpec<Visitor> spec) {
        this.spec = spec;
        this.id = null;
    }
}
