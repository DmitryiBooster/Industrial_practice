package learning.spring.forproizvodsta.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherCreateDTO {

    @NotBlank(message = "Имя необходимо")
    @Size(min = 2, max = 255, message = "Длина имени должна быть в диапазоне от 2 до 255 символов")
    private String firstName;

    @NotBlank(message = "Фамилия необходима")
    @Size(min = 2, max = 255, message = "Длина фамилии должна быть в диапазоне от 2 до 255 символов")
    private String lastName;

    @Min(value = 6, message = "Возраст должен быть не меньше 6")
    @Max(value = 100, message = "Возраст должен быть не больше 100")
    private int age;

    @Email(message = "Email должен быть валидным")
    @NotBlank(message = "Email должен присутствовать")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Телефон должен содержать от 9 до 15 цифр, ")
    private String phone;

    @NotBlank(message = "Необходимо указать тип образования")
    @Size(min = 5, max = 255, message = "Образование должно содержать от 5 до 255 символов")
    private String educational;

    @NotBlank(message = "Необходимо указать статус")
    @Size(min = 2, max = 30)
    private String status;

    public TeacherCreateDTO(String firstName, String lastName, int age, String email, String phone, String educational, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.educational = educational;
        this.status = status;
    }

    public TeacherCreateDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
