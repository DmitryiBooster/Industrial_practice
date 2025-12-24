package learning.spring.proizvodstvo2.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "courses",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "instructor"})
)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Instructor name cannot be empty")
    @Size(min = 3, max = 255, message = "Instructor name must be between 3 and 255 characters")
    private String instructor;

    @Column(nullable = false)
    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Duration must be at least 1 hour")
    @Max(value = 1000, message = "Duration cannot exceed 1000 hours")
    private Integer duration;

    @Column(nullable = false)
    @NotBlank(message = "Category cannot be empty")
    @Size(min = 2, max = 100, message = "Category must be between 2 and 100 characters")
    private String category;

    @Column(nullable = false)
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Price cannot exceed 999999.99")
    private Double price;

    @Column(nullable = false)
    @NotNull(message = "Max students cannot be null")
    @Min(value = 1, message = "Max students must be at least 1")
    @Max(value = 1000, message = "Max students cannot exceed 1000")
    private Integer maxStudents;

    @Column(nullable = false)
    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;

    public Course() {
    }

    public Course(String title, String description, String instructor, Integer duration, String category, Double price, Integer maxStudents, LocalDate startDate) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.duration = duration;
        this.category = category;
        this.price = price;
        this.maxStudents = maxStudents;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", instructor='" + instructor + '\'' +
                ", duration=" + duration +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", maxStudents=" + maxStudents +
                ", startDate=" + startDate +
                '}';
    }
}
