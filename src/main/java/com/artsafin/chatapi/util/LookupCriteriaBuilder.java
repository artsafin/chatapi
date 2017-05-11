package com.artsafin.chatapi.util;

public interface LookupCriteriaBuilder {

    @FunctionalInterface
    interface BracketsClosure {
        void call();
    }

    enum Flags {
        NULLABLE
    }

    enum Operation {
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        GREATER_OR_EQUALS,
        LESS_THAN,
        LESS_OR_EQUALS,
    }

    LookupCriteriaBuilder and(String key, Operation op, Object value, Flags... flags);
    LookupCriteriaBuilder or(String key, Operation op, Object value, Flags... flags);
    LookupCriteriaBuilder brackets(BracketsClosure bc);
}
