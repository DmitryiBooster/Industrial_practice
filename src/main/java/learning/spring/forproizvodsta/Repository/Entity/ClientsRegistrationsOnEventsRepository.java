package learning.spring.forproizvodsta.Repository.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientsRegistrationsOnEventsRepository extends JpaRepository<ClientsRegistrationsOnEvents, Long> {

    List<ClientsRegistrationsOnEvents> findByEventId(Long eventId);

    List<ClientsRegistrationsOnEvents> findByUserId(Long userId);

    boolean existsByEventIdAndUserId(Long eventId, Long userId);

    Optional<ClientsRegistrationsOnEvents> findByEventIdAndUserId(Long eventId, Long userId);

    long countByEventId(Long eventId);
}
