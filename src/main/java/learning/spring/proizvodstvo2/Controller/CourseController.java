package learning.spring.proizvodstvo2.Controller;

import jakarta.validation.Valid;
import learning.spring.proizvodstvo2.DTO.CourseUpdateDTO;
import learning.spring.proizvodstvo2.Entity.Course;
import learning.spring.proizvodstvo2.Repository.CourseRepository;
import learning.spring.proizvodstvo2.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseService courseService;

    public CourseController(CourseRepository courseRepository, CourseService courseService) {
        this.courseRepository = courseRepository;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @GetMapping("/course")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/course/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/course/category/{category}")
    public List<Course> getCoursesByCategory(@PathVariable String category) {
        return courseService.findByCategory(category);
    }

    @GetMapping("/course/instructor/{instructor}")
    public List<Course> getCoursesByInstructor(@PathVariable String instructor) {
        return courseService.findByInstructor(instructor);
    }

    @PostMapping("/course")
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable long id,
                                               @Valid @RequestBody CourseUpdateDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setInstructor(courseDTO.getInstructor());
        course.setDuration(courseDTO.getDuration());
        course.setCategory(courseDTO.getCategory());
        course.setPrice(courseDTO.getPrice());
        course.setMaxStudents(courseDTO.getMaxStudents());

        LocalDate startDate = LocalDate.parse(courseDTO.getStartDate());
        course.setStartDate(startDate);

        Course updated = courseService.update(id, course);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/course/{id}")
    public void deleteCourse(@PathVariable long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            courseService.delete(id);
        } else {
            throw new IllegalArgumentException("Course with id " + id + " not found");
        }
    }
}
