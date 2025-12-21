package learning.spring.forproizvodsta.Repository.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(
        name = "clients_registrations_on_events",
        schema = "public",
        uniqueConstraints = @UniqueConstraint(columnNames = {"fk_id_events", "fk_id_user"})

)
public class ClientsRegistrationsOnEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //___

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "fk_id_events",
            referencedColumnName = "id",
            nullable = false
    )
    private Events event;
    //___
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "fk_id_user",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;
    //___
    @Column(
            name = "registration_date",
            columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime registrationDate;

    public ClientsRegistrationsOnEvents(Events fkIdEvent, User fkIdUser, LocalDateTime registrationDate) {
        this.event = fkIdEvent;
        this.user = fkIdUser;
        this.registrationDate = registrationDate;
    }

    public ClientsRegistrationsOnEvents() {
    }

    public ClientsRegistrationsOnEvents(Events event, User user) {
        this.event = event;
        this.user = user;
    }
}
