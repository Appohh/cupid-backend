package cupid.main.controller.dto.Handler.CustomExceptions;

public class AlreadyExistException extends RuntimeException {
    @lombok.Getter
    private final String message;

    public AlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
