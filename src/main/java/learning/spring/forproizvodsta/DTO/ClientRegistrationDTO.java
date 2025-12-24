package learning.spring.forproizvodsta.DTO;

import java.time.LocalDateTime;

public class ClientRegistrationDTO {
    private Long id;
    private Long eventId;
    private String eventName;
    private Long userId;
    private String userName;  // firstName + lastName
    private LocalDateTime registrationDate;

    // Конструктор
    public ClientRegistrationDTO(
            Long id,
            Long eventId,
            String eventName,
            Long userId,
            String userName,
            LocalDateTime registrationDate) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.userId = userId;
        this.userName = userName;
        this.registrationDate = registrationDate;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
