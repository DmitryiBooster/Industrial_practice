package learning.spring.forproizvodsta.Repository.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(
        name = "user",
        schema = "public"
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", columnDefinition = "VARCHAR", length = 255, nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR", length = 255, nullable = false)
    private String lastName;

    @Column(name = "age", columnDefinition = "INT", nullable = false)
    private int age;

    @Column(name = "phone", columnDefinition = "VARCHAR", length = 16)
    private String phone;

    @Column(name = "email", columnDefinition = "VARCHAR", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "educational", columnDefinition = "VARCHAR", length = 255)
    private String educational; // Поле для учителей

    @Column(name = "status", columnDefinition = "VARCHAR", length = 30)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_role", referencedColumnName = "id", nullable = false)
    private Role role;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Events> teachingEvents = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ClientsRegistrationsOnEvents> registrations = new HashSet<>();

    public User(String firstName, String lastName, int age, String phone,
                String email, String educational, String status, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.educational = educational;
        this.status = status;
        this.role = role;
    }

    public User(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public User() {
    }

    public boolean isTeacher() {
        return role != null && "TEACHER".equals(role.getRole());
    }

    public boolean isClient() {
        return role != null && "CLIENT".equals(role.getRole());
    }
}
