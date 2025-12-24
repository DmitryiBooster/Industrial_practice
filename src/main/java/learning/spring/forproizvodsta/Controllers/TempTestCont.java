package learning.spring.forproizvodsta.Controllers;

import jakarta.validation.Valid;
import learning.spring.forproizvodsta.DTO.TeacherCreateDTO;
import learning.spring.forproizvodsta.DTO.TeacherResponseDTO;
import learning.spring.forproizvodsta.DTO.UserCreateDTO;
import learning.spring.forproizvodsta.DTO.UserResponseDTO;
import learning.spring.forproizvodsta.Repository.Entity.User;
import learning.spring.forproizvodsta.Services.UserService;
import learning.spring.forproizvodsta.Services.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("temp/example/users")
public class TempTestCont {

    private static final Logger log = LoggerFactory.getLogger(ValidationService.class);

    private final UserService userService;

    public TempTestCont(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO dto) {
        log.info("POST /api/users - создание клиента");
        User user = userService.createUser(dto);
        return ResponseEntity.ok(mapToResponseDTO(user));
    }

    @PostMapping("/teacher")
    public ResponseEntity<TeacherResponseDTO> createTeacher(@Valid @RequestBody TeacherCreateDTO dto) {
        log.info("POST /api/users/teacher - создание учителя");
        User user = userService.createTeacher(dto);
        return ResponseEntity.ok(mapToTeacherResponseDTO(user));
    }

    @PostMapping("/admin")
    public ResponseEntity<UserResponseDTO> createAdmin(@Valid @RequestBody UserCreateDTO dto) {
        log.info("POST /api/users/admin - создание админа");
        User user = userService.createAdmin(dto);
        return ResponseEntity.ok(mapToResponseDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        log.info("GET /api/users/{} - получение пользователя", id);
        User user = userService.getUserById(id);
        return ResponseEntity.ok(mapToResponseDTO(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        log.info("GET /api/users - получение всех пользователей");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(this::mapToResponseDTO).toList());
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().getRole(),
                user.getCreatedAt()
        );
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<UserResponseDTO>> getAllTeachers() {
        log.info("GET /api/users/teachers - получение всех учителей");
        List<User> teachers = userService.getAllTeacher();
        return ResponseEntity.ok(teachers.stream().map(this::mapToResponseDTO).toList());
    }

    @GetMapping("/clients")
    public ResponseEntity<List<UserResponseDTO>> getAllClients() {
        log.info("GET /api/users/clients - получение всех клиентов");
        List<User> clients = userService.getAllClients();
        return ResponseEntity.ok(clients.stream().map(this::mapToResponseDTO).toList());
    }

    @GetMapping("/admins")
    public ResponseEntity<List<UserResponseDTO>> getAllAdmins() {
        log.info("GET /api/users/admins - получение всех админов");
        List<User> admins = userService.getAllAdmins();
        return ResponseEntity.ok(admins.stream().map(this::mapToResponseDTO).toList());
    }

    private TeacherResponseDTO mapToTeacherResponseDTO(User user) {
        return new TeacherResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getEmail(),
                user.getPhone(),
                user.getEducational(),
                user.getStatus(),
                user.getRole().getRole(),
                user.getCreatedAt()
        );
    }

}
