package learning.spring.forproizvodsta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String educational;
    private String status;
    private String role;
    private LocalDateTime createdAt;
}
