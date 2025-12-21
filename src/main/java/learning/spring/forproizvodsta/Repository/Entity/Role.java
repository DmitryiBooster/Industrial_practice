package learning.spring.forproizvodsta.Repository.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(
        name = "role",
        schema = "public"
)

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role",
            columnDefinition = "VARCHAR",
            length = 255, nullable = false)
    private String role;

    @Column(name = "description")
    private String description;

    public Role(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }
}
