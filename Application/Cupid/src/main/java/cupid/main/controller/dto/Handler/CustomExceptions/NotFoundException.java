package cupid.main.controller.dto.Handler.CustomExceptions;

public class NotFoundException extends RuntimeException {
    @lombok.Getter
    private final String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
