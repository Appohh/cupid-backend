package cupid.main.controller.dto.Handler.custom_exceptions;

public class InvalidException extends RuntimeException {
    @lombok.Getter
    private final String message;

    public InvalidException(String message) {
        super(message);
        this.message = message;
    }
}
