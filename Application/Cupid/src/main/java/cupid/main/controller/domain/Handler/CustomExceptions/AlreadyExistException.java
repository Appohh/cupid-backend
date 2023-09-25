package cupid.main.controller.domain.Handler.CustomExceptions;

public class AlreadyExistException extends RuntimeException {
    @lombok.Getter
    private final String message;

    public AlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
