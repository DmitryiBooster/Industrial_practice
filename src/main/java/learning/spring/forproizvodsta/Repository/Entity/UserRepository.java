package learning.spring.forproizvodsta.Repository.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>  findByFirstNameAndLastName(String firstName, String lastName);

    Optional<User>  findByEmail(String email);

    Optional<User> findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.role.role = 'TEACHER'")
    List<User> findAllTeachers();

    @Query("SELECT u FROM User u WHERE u.role.role = 'CLIENT'")
    List<User> findAllClients();

    @Query("SELECT u FROM User u WHERE u.role.role = 'ADMIN'")
    List<User> findAllAdmins();
}
