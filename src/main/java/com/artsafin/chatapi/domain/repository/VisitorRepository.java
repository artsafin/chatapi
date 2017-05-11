package com.artsafin.chatapi.domain.repository;

import com.artsafin.chatapi.domain.visitor.PersistentVisitor;
import com.artsafin.chatapi.domain.visitor.VisitorNotFoundException;
import com.artsafin.chatapi.util.LookupSpec;
import com.artsafin.chatapi.domain.visitor.Visitor;
import org.jetbrains.annotations.Nullable;

public interface VisitorRepository {
    void add(PersistentVisitor visitor);

    @Nullable PersistentVisitor findOneBy(LookupSpec<Visitor> visitorSpec);

    PersistentVisitor findById(Visitor.Id visitorId);

    PersistentVisitor requireById(Visitor.Id visitorId) throws VisitorNotFoundException;
}
