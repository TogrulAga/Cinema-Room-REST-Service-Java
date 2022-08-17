package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TicketException extends RuntimeException {
    private final HttpStatus httpStatus;
    public TicketException(String cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

