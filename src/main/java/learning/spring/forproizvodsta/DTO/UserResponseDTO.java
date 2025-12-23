package learning.spring.forproizvodsta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String role;  // только имя роли, не весь объект
    private LocalDateTime createdAt;
}
