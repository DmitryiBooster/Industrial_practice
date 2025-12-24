package learning.spring.forproizvodsta.Services;


import jakarta.transaction.Transactional;
import learning.spring.forproizvodsta.DTO.ClientRegistrationDTO;
import learning.spring.forproizvodsta.MyExceptions.AlreadyRegisteredException;
import learning.spring.forproizvodsta.MyExceptions.EventNotFoundException;
import learning.spring.forproizvodsta.MyExceptions.RegistrationNotFoundException;
import learning.spring.forproizvodsta.MyExceptions.UserNotFoundException;
import learning.spring.forproizvodsta.Repository.Entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Какой должен быть функционал:
 * <p>
 * - Регистрация клиента на МП
 * - Получение МП, на которые зарегистрирован клиент
 * - Отписка от МП
 * - Проверка: зарегистрирован ли клиент на МП
 * - Валидация (например, клиент уже зарегистрирован)
 **/

@Service
@Transactional
public class EventsRegistrationService {

    private static final Logger log = LoggerFactory.getLogger(EventsRegistrationService.class);

    private final ClientsRegistrationsOnEventsRepository registrationRepository;
    private final UserRepository userRepository;
    private final EventsRepository eventsRepository;

    public EventsRegistrationService(ClientsRegistrationsOnEventsRepository registrationRepository, UserRepository userRepository, EventsRepository eventsRepository) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.eventsRepository = eventsRepository;
    }

    public ClientsRegistrationsOnEvents registerUserToEvent(Long userId, Long eventId) {
        log.info("Регистрация клиента: " + userId + " на событие: " + eventId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Клиент с ID " + userId + " не найден"));

        Events event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Событие с ID " + eventId + " не найдено"));

        ClientsRegistrationsOnEvents registration = new ClientsRegistrationsOnEvents(event, user);

        ClientsRegistrationsOnEvents saved = registrationRepository.save(registration);
        log.info("Клиент: " + userId + " успешно зарегистрирован на событие: " + eventId);

        return saved;
    }

    public void cancelRegistration(Long registrationId) {
        log.info("Отмена регистрации: " + registrationId);

        ClientsRegistrationsOnEvents registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RegistrationNotFoundException("Регистрация с ID " + registrationId + " не найдена"));

        registrationRepository.delete(registration);
        log.info("Регистрация: " + registrationId + " отменена и удалена");
    }

    public List<ClientsRegistrationsOnEvents> getEventRegistrations(Long eventId) {
        log.info("Получение регистраций на событие: " + eventId);

        if (!eventsRepository.existsById(eventId)) {
            throw new EventNotFoundException("Событие с ID " + eventId + " не найдено");
        }

        return registrationRepository.findByEventId(eventId);
    }

    public List<ClientsRegistrationsOnEvents> getUserRegistrations(Long userId) {
        log.info("Получение событий клиента: " + userId);

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Клиент с ID " + userId + " не найден");
        }

        return registrationRepository.findByUserId(userId);
    }

    public Long getRegistrationCount(Long eventId) {
        log.info("Получение количества участников события: " + eventId);
        return registrationRepository.countByEventId(eventId);
    }

    public Boolean isUserRegistered(Long eventId, Long userId) {
        log.info("Проверка регистрации клиента: " + userId + " на событие: " + eventId);
        return registrationRepository.existsByEventIdAndUserId(eventId, userId);
    }

    public ClientsRegistrationsOnEvents getRegistrationById(Long registrationId) {
        log.info("Получение регистрации: " + registrationId);
        return registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RegistrationNotFoundException("Регистрация с ID " + registrationId + " не найдена"));
    }

    public ClientRegistrationDTO  getRegistrationDTOById(Long registrationId) {
        ClientsRegistrationsOnEvents registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RegistrationNotFoundException("Регистрация не найдена"));

        String fullName = registration.getUser().getFirstName() + " " +
                registration.getUser().getLastName();

        return new ClientRegistrationDTO(
                registration.getId(),
                registration.getEvent().getId(),
                registration.getEvent().getName(),
                registration.getUser().getId(),
                fullName,
                registration.getRegistrationDate()
        );
    }

    public List<ClientRegistrationDTO> getEventRegistrationsDTOs(Long eventId) {
        List<ClientsRegistrationsOnEvents> registrations = getEventRegistrations(eventId);

        return registrations.stream()
                .map(reg -> new ClientRegistrationDTO(
                        reg.getId(),
                        reg.getEvent().getId(),
                        reg.getEvent().getName(),
                        reg.getUser().getId(),
                        reg.getUser().getFirstName() + " " + reg.getUser().getLastName(),
                        reg.getRegistrationDate()
                ))
                .toList();
    }

    public List<ClientRegistrationDTO> getUserRegistrationsDTOs(Long userId) {
        List<ClientsRegistrationsOnEvents> registrations = getUserRegistrations(userId);

        return registrations.stream()
                .map(reg -> new ClientRegistrationDTO(
                        reg.getId(),
                        reg.getEvent().getId(),
                        reg.getEvent().getName(),
                        reg.getUser().getId(),
                        reg.getUser().getFirstName() + " " + reg.getUser().getLastName(),
                        reg.getRegistrationDate()
                ))
                .toList();
    }
}
