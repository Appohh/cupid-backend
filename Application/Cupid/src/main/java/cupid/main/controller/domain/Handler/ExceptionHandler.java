package cupid.main.controller.domain.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionHandler {
    public static void handleResponseStatus(int errorCode, String errorMessage) {
        HttpStatus status = switch (errorCode) {
            case 400 -> HttpStatus.BAD_REQUEST;
            case 404 -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        throw new ResponseStatusException(status, errorMessage);
    }
}
