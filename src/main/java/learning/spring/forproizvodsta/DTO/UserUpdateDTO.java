package learning.spring.forproizvodsta.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {
    @Size(min = 2, max = 255, message = "Длина имени должно быть в диапазоне от 2 до 255 символов")
    private String firstName;

    @Size(min = 2, max = 255, message = "Длина имени должно быть в диапазоне от 2 до 255 символов")
    private String lastName;

    @Min(value = 6, message = "Возраст должен быть не меньше 6")
    @Max(value = 100, message = "Возраст должен быть не больше 100")
    private int age;

    @Email(message = "Email должен быть валидным")
    private String email;

    @Pattern(regexp = "^\\\\+?[0-9]{9,15}$", message = "Телефон должен содержать от 9 до 15 цифр")
    private String phone;

    public UserUpdateDTO(String firstName, String lastName, int age, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public UserUpdateDTO() {
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
}
