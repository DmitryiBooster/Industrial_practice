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

    /**
     * Получить все курсы
     * @return список всех курсов
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Получить курс по ID
     * @param id ID курса
     * @return курс
     * @throws RuntimeException если курс не найден
     */
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    /**
     * Создать новый курс
     * @param course объект курса для создания
     * @return созданный курс
     * @throws IllegalArgumentException если курс с таким названием уже существует
     */
    public Course create(Course course) {
        Optional<Course> existingCourse = Optional.ofNullable(courseRepository.findByTitle(course.getTitle()));
        if (existingCourse.isPresent()) {
            throw new IllegalArgumentException("Course with title '" + course.getTitle() + "' already exists");
        }
        return courseRepository.save(course);
    }

    /**
     * Обновить существующий курс
     * @param id ID курса для обновления
     * @param courseDetails новые данные курса
     * @return обновленный курс
     * @throws RuntimeException если курс не найден
     * @throws IllegalArgumentException если новое название уже занято
     */
    public Course update(Long id, Course courseDetails) {
        Course course = getCourseById(id);

        // Проверяем, не занято ли новое название другим курсом
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

    /**
     * Удалить курс по ID
     * @param id ID курса для удаления
     * @throws IllegalArgumentException если курс не найден
     */
    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalArgumentException("Course with id " + id + " does not exist");
        }
        courseRepository.deleteById(id);
    }

    /**
     * Найти все курсы по категории
     * @param category категория для поиска
     * @return список курсов в этой категории
     */
    public List<Course> findByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    /**
     * Найти все курсы конкретного преподавателя
     * @param instructor имя преподавателя
     * @return список курсов этого преподавателя
     */
    public List<Course> findByInstructor(String instructor) {
        return courseRepository.findByInstructor(instructor);
    }
}
