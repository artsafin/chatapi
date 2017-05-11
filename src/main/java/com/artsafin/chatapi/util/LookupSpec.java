package com.artsafin.chatapi.util;

public interface LookupSpec<T> {
    void buildCriterias(LookupCriteriaBuilder cb);
}
