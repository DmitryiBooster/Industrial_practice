package learning.spring.proizvodstvo2.DTO;

import jakarta.validation.constraints.*;

public class CourseUpdateDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 255, message = "Title must be 3-255 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    @NotBlank(message = "Instructor cannot be blank")
    @Size(min = 3, max = 255)
    private String instructor;

    @NotNull(message = "Duration cannot be null")
    @Min(value = 1)
    @Max(value = 1000)
    private Integer duration;

    @NotBlank(message = "Category cannot be blank")
    @Size(min = 2, max = 100)
    private String category;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "999999.99")
    private Double price;

    @NotNull(message = "Max students cannot be null")
    @Min(value = 1)
    @Max(value = 1000)
    private Integer maxStudents;

    public CourseUpdateDTO() {
    }

    public CourseUpdateDTO(String title, String description, String instructor, Integer duration, String category, Double price, Integer maxStudents, String startDate) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.duration = duration;
        this.category = category;
        this.price = price;
        this.maxStudents = maxStudents;
        this.startDate = startDate;
    }

    @NotNull(message = "Start date cannot be null")
    private String startDate;  // Строка, не LocalDate

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getMaxStudents() { return maxStudents; }
    public void setMaxStudents(Integer maxStudents) { this.maxStudents = maxStudents; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
}