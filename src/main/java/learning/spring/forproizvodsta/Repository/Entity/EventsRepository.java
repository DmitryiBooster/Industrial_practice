package learning.spring.forproizvodsta.Repository.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventsRepository extends JpaRepository<Events, Long> {

    List<Events> findByNameContainingIgnoreCase(String name);

    List<Events> findByTeacherId(Long teacherId);

    @Query("SELECT e FROM Events e WHERE e.eventTime > CURRENT_TIMESTAMP ORDER BY e.eventTime ASC")
    List<Events> findUpcomingEvents();

    boolean existsByNameIgnoreCase(String name);
}
