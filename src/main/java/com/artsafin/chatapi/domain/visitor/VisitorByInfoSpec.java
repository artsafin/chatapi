package com.artsafin.chatapi.domain.visitor;

import com.artsafin.chatapi.util.LookupCriteriaBuilder;
import com.artsafin.chatapi.util.LookupSpec;

import static com.artsafin.chatapi.domain.visitor.PersistentVisitor.*;
import static com.artsafin.chatapi.util.LookupCriteriaBuilder.Flags.*;
import static com.artsafin.chatapi.util.LookupCriteriaBuilder.Operation.*;

public class VisitorByInfoSpec implements LookupSpec<Visitor> {
    private final VisitInfo visitInfo;

    public VisitorByInfoSpec(VisitInfo visitInfo) {
        this.visitInfo = visitInfo;
    }

    @Override
    public void buildCriterias(LookupCriteriaBuilder cb) {
        cb.brackets(() -> cb
                .and(NAME, EQUALS, visitInfo.name, NULLABLE)
                .and(LANGUAGE, EQUALS, visitInfo.language)
                .and(COUNTRY, EQUALS, visitInfo.country)
        );
        cb.or(PHONE, EQUALS, visitInfo.phone);
        cb.or(CLIENT_ID, EQUALS, visitInfo.clientId);
    }


}
