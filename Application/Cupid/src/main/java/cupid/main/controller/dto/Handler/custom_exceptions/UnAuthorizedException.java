package cupid.main.controller.dto.Handler.custom_exceptions;

public class UnAuthorizedException extends RuntimeException {
    @lombok.Getter
    private final String message;

    public UnAuthorizedException(String message) {
        super(message);
        this.message = message;
    }
}