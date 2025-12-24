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

    public User(String firstName, String lastName, int age, String email, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public User() {
    }

    public boolean isTeacher() {
        return role != null && "TEACHER".equals(role.getRole());
    }

    public boolean isClient() {
        return role != null && "CLIENT".equals(role.getRole());
    }

    public boolean isAdmin() {
        return role != null && "ADMIN".equals(role.getRole());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Events> getTeachingEvents() {
        return teachingEvents;
    }

    public void setTeachingEvents(Set<Events> teachingEvents) {
        this.teachingEvents = teachingEvents;
    }

    public Set<ClientsRegistrationsOnEvents> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<ClientsRegistrationsOnEvents> registrations) {
        this.registrations = registrations;
    }
}
