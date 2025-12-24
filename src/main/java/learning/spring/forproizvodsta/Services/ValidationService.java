package learning.spring.forproizvodsta.Services;

import learning.spring.forproizvodsta.MyExceptions.InvalidEmailException;
import learning.spring.forproizvodsta.MyExceptions.InvalidPhoneException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Slf4j
public class ValidationService {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{9,15}$");

    public void validateEmail(String email) {
        log.debug("Валидация email: " + email);

        if (email == null || email.isBlank()) {
            log.warn("Email пустой или null");
            throw new InvalidEmailException("Email не может быть пустым");
        }

        if (email.length() > 255) {
            log.warn("Email превышает лимит 255 символов");
            throw new InvalidEmailException("Email не может быть длинее 255 символов");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            log.warn("Email не прошел валидацию");
            throw new InvalidEmailException("Email имеет неправильный формат");
        }

        log.debug("Email: " + email + " прошел валидацию");
    }

    public void validatePhone(String phone) {
        log.debug("Валидация телефона: " + phone);

        if (phone == null || phone.isBlank()) {
            log.warn("Телефон пустой или null");
            throw new InvalidPhoneException("Телефон не может быть пустым");
        }

        String cleanPhone = phone.replaceAll("[\\s-]", "");

        if (cleanPhone.length() < 9 || cleanPhone.length() > 15) {
            log.warn("Телефон имеет неправильную длину: " + cleanPhone.length());
            throw new InvalidPhoneException("Телефон должен содержать от 9 до 15 цифр");
        }

        if (!PHONE_PATTERN.matcher(cleanPhone).matches()) {
            log.warn("Телеон не прошел валидацию");
            throw new InvalidPhoneException("Телефон имеет неправильный формат");
        }

        log.debug("Телефон: " + cleanPhone + " прошел валидацию");
    }

    public String cleanPhoneNumber(String phone) {
        if (phone == null) {
            return null;
        }
        // Удаляем всё кроме цифр и +
        return phone.replaceAll("[^+0-9]", "");
    }

    public String normalizeEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.toLowerCase().trim();
    }

    public boolean isNameValid(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        // Разрешаем буквы (латиница и кириллица) и пробелы
        return name.matches("^[a-zA-Zа-яА-ЯёЁ\\s-]+$");
    }

    public boolean isPhoneValid(String phone) {
        if (phone == null || phone.isBlank()) {
            return false;
        }
        String cleanedPhone = phone.replaceAll("[\\s-]", "");
        if (cleanedPhone.length() < 9 || cleanedPhone.length() > 15) {
            return false;
        }
        return PHONE_PATTERN.matcher(cleanedPhone).matches();
    }

    public boolean isEmailValid(String email) {
        if (email == null || email.isBlank() || email.length() > 255) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
