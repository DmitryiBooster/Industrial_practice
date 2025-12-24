package learning.spring.proizvodstvo2.Service;

import jakarta.transaction.Transactional;
import learning.spring.proizvodstvo2.Entity.Course;

import learning.spring.proizvodstvo2.Repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course create(Course course) {
        Optional<Course> existingCourse = Optional.ofNullable(courseRepository.findByTitle(course.getTitle()));
        if (existingCourse.isPresent()) {
            throw new IllegalArgumentException("Course with title '" + course.getTitle() + "' already exists");
        }
        return courseRepository.save(course);
    }

    public Course update(Long id, Course courseDetails) {
        Course course = getCourseById(id);


        if (!course.getTitle().equals(courseDetails.getTitle())) {
            Optional<Course> existingCourse = Optional.ofNullable(courseRepository.findByTitle(courseDetails.getTitle()));
            if (existingCourse.isPresent()) {
                throw new IllegalArgumentException("Course with title '" + courseDetails.getTitle() + "' already exists");
            }
        }

        // Обновляем все поля
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setInstructor(courseDetails.getInstructor());
        course.setDuration(courseDetails.getDuration());
        course.setCategory(courseDetails.getCategory());
        course.setPrice(courseDetails.getPrice());
        course.setMaxStudents(courseDetails.getMaxStudents());
        course.setStartDate(courseDetails.getStartDate());

        return courseRepository.save(course);
    }

    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalArgumentException("Course with id " + id + " does not exist");
        }
        courseRepository.deleteById(id);
    }

    public List<Course> findByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public List<Course> findByInstructor(String instructor) {
        return courseRepository.findByInstructor(instructor);
    }
}
