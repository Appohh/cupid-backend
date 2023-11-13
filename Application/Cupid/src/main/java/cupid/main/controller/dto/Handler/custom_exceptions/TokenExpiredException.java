package cupid.main.controller.dto.Handler.custom_exceptions;

public class TokenExpiredException extends RuntimeException {
    @lombok.Getter
    private final String message;

    public TokenExpiredException(String message) {
        super(message);
        this.message = message;
    }
}