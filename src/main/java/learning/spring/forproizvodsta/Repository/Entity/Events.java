package learning.spring.forproizvodsta.Repository.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(
        name = "events",
        schema = "public"
)
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //___

    @Column(name = "name",
            nullable = false,
            columnDefinition = "VARCHAR",
            length = 255
    )
    private String name;
    //___
    @Column(name = "event_time",
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime eventTime;
    //___
    @Column(
            name = "duration_event_time",
            columnDefinition = "TIME"
    )
    private LocalTime durationEventTime;

    @Column(name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "fk_id_teacher",
            referencedColumnName = "id",
            nullable = false
    )
    private User teacher;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<ClientsRegistrationsOnEvents> registrations = new HashSet<>();

    public Events(String name, LocalDateTime eventTime, LocalTime durationEventTime,
                  String description, User teacher) {
        this.name = name;
        this.eventTime = eventTime;
        this.durationEventTime = durationEventTime;
        this.description = description;
        this.teacher = teacher;
    }

    public Events(String name, User teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public Events() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public LocalTime getDurationEventTime() {
        return durationEventTime;
    }

    public void setDurationEventTime(LocalTime durationEventTime) {
        this.durationEventTime = durationEventTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<ClientsRegistrationsOnEvents> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<ClientsRegistrationsOnEvents> registrations) {
        this.registrations = registrations;
    }
}
