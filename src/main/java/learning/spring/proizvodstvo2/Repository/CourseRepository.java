package learning.spring.proizvodstvo2.Repository;



import learning.spring.proizvodstvo2.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByTitle(String title);

    List<Course> findByCategory(String category);

    List<Course> findByInstructor(String instructor);
}
