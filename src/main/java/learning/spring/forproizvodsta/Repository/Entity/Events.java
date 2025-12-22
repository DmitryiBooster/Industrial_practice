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
}
