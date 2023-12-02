package cupid.main.config.custom_exceptions;

public class FileSaveException extends RuntimeException {
    @lombok.Getter
    private final String message;

    public FileSaveException(String message) {
        super(message);
        this.message = message;
    }
}