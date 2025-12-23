package learning.spring.forproizvodsta.MyExceptions;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super(message);
    }

    public InvalidPhoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
