package net.mahtabalam;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name="start_time")
    private OffsetDateTime startTime;

    public Event(String name, OffsetDateTime startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
