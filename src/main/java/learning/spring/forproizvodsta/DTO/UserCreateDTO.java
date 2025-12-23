package learning.spring.forproizvodsta.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
    // @NotBlank — проверяет, что строка НЕ пустая
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
}
