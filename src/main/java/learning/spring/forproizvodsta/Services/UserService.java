package learning.spring.forproizvodsta.Services;


import jakarta.transaction.Transactional;
import learning.spring.forproizvodsta.DTO.TeacherCreateDTO;
import learning.spring.forproizvodsta.DTO.TeacherUpdateDTO;
import learning.spring.forproizvodsta.DTO.UserCreateDTO;
import learning.spring.forproizvodsta.DTO.UserUpdateDTO;
import learning.spring.forproizvodsta.MyExceptions.DuplicateEmailException;
import learning.spring.forproizvodsta.MyExceptions.DuplicatePhoneException;
import learning.spring.forproizvodsta.Repository.Entity.Role;
import learning.spring.forproizvodsta.Repository.Entity.RoleRepository;
import learning.spring.forproizvodsta.Repository.Entity.User;
import learning.spring.forproizvodsta.Repository.Entity.UserRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import learning.spring.forproizvodsta.MyExceptions.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import learning.spring.forproizvodsta.MyExceptions.RoleNotFoundException;
import java.util.List;


/**
 * Какой должен быть функционал:
 * <p>
 * - Создание пользователя (с ролью)
 * - Получение пользователя по ID, email, имени
 * - Получение всех учителей
 * - Получение всех клиентов
 * - Редактирование профиля пользователя
 * - Проверка роли пользователя
 * - Валидация данных (email, phone уникальность)
 **/
@Service
@Transactional

public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ValidationService validationService;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.validationService = validationService;
    }
    //                     |
                    /** CREATE **/
    //                     |
    public User createUser(UserCreateDTO dto) {
        log.info("Создание клиента с email: " + dto.getEmail());
        validateNewUser(dto);

        Role clientRole = roleRepository.findById(RoleConstants.CLIENT_ROLE_ID)
                .orElseThrow(() -> new RoleNotFoundException("CLIENT роль не найдена"));

        User user = new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge(),
                dto.getEmail(),
                clientRole

        );

        user.setPhone(dto.getPhone());
        user.setRole(clientRole);

        User savedUser = userRepository.save(user);
        log.info("Клиент успешно создан с id: " + savedUser.getId());
        return savedUser;
    }

    public User createTeacher(TeacherCreateDTO dto) {
        log.info("Создание учителя с email: {}", dto.getEmail());

        validateNewTeacher(dto);

        Role teacherRole = roleRepository.findById(RoleConstants.TEACHER_ROLE_ID)
                .orElseThrow(() -> new RoleNotFoundException("TEACHER роль не найдена"));

        User user = new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge(),
                dto.getEmail(),
                teacherRole
        );
        user.setPhone(dto.getPhone());
        user.setEducational(dto.getEducational());    // специальное поле для учителя
        user.setStatus(dto.getStatus());              // специальное поле для учителя
        user.setRole(teacherRole);

        User savedUser = userRepository.save(user);
        log.info("Учитель успешно создан с id: {}", savedUser.getId());
        return savedUser;
    }

    public User createAdmin(UserCreateDTO dto) {
        log.info("Создание администратора с email: {}", dto.getEmail());

        validateNewUser(dto);

        Role adminRole = roleRepository.findById(RoleConstants.ADMIN_ROLE_ID)
                .orElseThrow(() -> new RoleNotFoundException("ADMIN роль не найдена"));

        User user = new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge(),
                dto.getEmail(),
                adminRole
        );
        user.setPhone(dto.getPhone());
        user.setRole(adminRole);

        User savedUser = userRepository.save(user);
        log.info("Администратор успешно создан с id: {}", savedUser.getId());
        return savedUser;
    }
    //                     |
                     /** READ **/
    //                     |
    public User getUserByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new RuntimeException("Пользователь с именем: " + firstName + " и фамилией: " + lastName + " не был найден"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Пользователь с данной почтой: " + email + " не был найден"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь с данным id: " + id + " не был найден"));
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("Пользователь с данным номером телефона: " + phone + " не был найден"));
    }

    public List<User> getAllTeacher() {
        log.info("Получение всех учителей");
        return userRepository.findAllTeachers();
    }

    public List<User> getAllClients() {
        log.info("Получение всех клиентов");
        return userRepository.findAllClients();
    }

    public List<User> getAllAdmins() {
        log.info("Получение всех админов");
        return userRepository.findAllAdmins();
    }

    public List<User> getAllUsers() {
        log.info("Получение всех пользователей");
        return userRepository.findAll();
    }
    //                     |
                    /** UPDATE **/
    //                     |
    public User updateUser(Long id, UserUpdateDTO dto) {
        log.info("Редактирование учетной записи пользователя с id: " + id);

        User user = getUserById(id);
        if (dto.getFirstName() != null && !dto.getFirstName().isBlank()) {
            user.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null && !dto.getLastName().isBlank()) {
            user.setLastName(dto.getLastName());
        }

        if (dto.getAge() > 6) {
            user.setAge(dto.getAge());
        }

        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateEmailException("Email уже используется");
            }
            validationService.validateEmail(dto.getEmail());
            user.setEmail(dto.getEmail());
        }

        if (dto.getPhone() != null && !dto.getPhone().equals(user.getPhone())) {
            if (userRepository.existsByPhone(dto.getPhone())) {
                throw new DuplicatePhoneException("Номер телефона уже используется");
            }
            validationService.validatePhone(dto.getPhone());
            user.setPhone(dto.getPhone());
        }

        User updatedUser = userRepository.save(user);
        log.info("Редактирование учетной записи пользователя с id: " + id + " успешно завершено");
        return updatedUser;
    }

    public User updateTeacherProfile(Long id, TeacherUpdateDTO dto) {
        log.info("Редактирование учетной записи учителя с id: " + id);

        User user = getUserById(id);

        if (!user.isTeacher()) {
            throw new AccessDeniedException("Пользователь не является учителем");
        }

        if (dto.getFirstName() != null && !dto.getFirstName().isBlank()) {
            user.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null && !dto.getLastName().isBlank()) {
            user.setLastName(dto.getLastName());
        }

        if (dto.getAge() > 18) {
            user.setAge(dto.getAge());
        }

        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateEmailException("Email уже используется");
            }
            user.setEmail(dto.getEmail());
        }

        if (dto.getPhone() != null && !dto.getPhone().equals(user.getPhone())) {
            if (userRepository.existsByPhone(dto.getPhone())) {
                throw new DuplicatePhoneException("Номер телефона уже используется");
            }
            user.setPhone(dto.getPhone());
        }

        if (dto.getEducational() != null && !dto.getEducational().isBlank()) {
            user.setEducational(dto.getEducational());
        }

        if (dto.getStatus() != null && !dto.getStatus().isBlank()) {
            user.setStatus(dto.getStatus());
        }

        User updatedUser = userRepository.save(user);
        log.info("Редактирование учетной записи учителя с id: " + id + " успешно завершено");
        return updatedUser;
    }
    //                     |
                    /** DELETE **/
    //                     |
    public void deleteUserById(Long id) {
        log.info("Удаление пользователя с id: " + id);

        User user = getUserById(id);
        userRepository.deleteById(id);

        log.info("Пользователь с id: " + id + " успешно удален");
    }

    public void deleteUserByEmail(String email) {
        log.info("Удаление пользователя email: " + email);

        User user = getUserByEmail(email);  // проверка существования
        userRepository.delete(user);

        log.info("Пользователь с email " + email + " успешно удален");
    }


    private void validateNewUser(UserCreateDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email уже зарегистрирован");
        }

        if (dto.getPhone() != null &&
                userRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicatePhoneException("Номер телефона уже зарегистрирован");
        }

        validationService.validateEmail(dto.getEmail());
        if (dto.getPhone() != null) {
            validationService.validatePhone(dto.getPhone());
        }
    }

    private void validateNewTeacher(TeacherCreateDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email уже зарегистрирован");
        }

        if (dto.getPhone() != null &&
                userRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicatePhoneException("Номер телефона уже зарегистрирован");
        }

        validationService.validateEmail(dto.getEmail());
        if (dto.getPhone() != null) {
            validationService.validatePhone(dto.getPhone());
        }
    }


}
