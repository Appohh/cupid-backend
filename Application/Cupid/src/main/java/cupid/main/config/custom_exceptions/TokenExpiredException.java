package cupid.main.config.custom_exceptions;

public class TokenExpiredException extends RuntimeException {
    @lombok.Getter
    private final String message;

    public TokenExpiredException(String message) {
        super(message);
        this.message = message;
    }
}