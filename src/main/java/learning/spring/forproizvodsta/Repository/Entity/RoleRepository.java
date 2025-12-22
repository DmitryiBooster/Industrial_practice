package learning.spring.forproizvodsta.Repository.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String roleName);

    boolean existsByRole(String roleName);

}
