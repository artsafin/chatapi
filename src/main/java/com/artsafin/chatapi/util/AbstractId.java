package com.artsafin.chatapi.util;

import java.util.Objects;
import java.util.UUID;

public abstract class AbstractId<T extends AbstractId> {
    private final UUID id;

    public AbstractId(UUID value) {
        id = value;
    }

    public AbstractId() {
        this(UUID.randomUUID());
    }

    public AbstractId(String s) {
        this(UUID.fromString(s));
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractId)) return false;
        AbstractId that = (AbstractId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
