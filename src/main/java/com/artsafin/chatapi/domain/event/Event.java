package com.artsafin.chatapi.domain.event;

import java.time.Instant;

public class Event extends PersistentEvent {
    public enum Direction {
        IN,
        OUT
    }

    public enum Importance {
        LOW,
        MEDIUM,
        HIGH
    }

    private Instant ts;
    private final Importance importance;
    private final Direction dir;

    public Event(Instant ts, Direction dir) {
        this(ts, dir, Importance.MEDIUM);
    }

    public Event(Instant ts, Direction dir, Importance importance) {
        this.ts = ts;
        this.dir = dir;
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "Event{" +
                "ts=" + ts +
                ", importance=" + importance +
                ", dir=" + dir +
                '}';
    }
}
