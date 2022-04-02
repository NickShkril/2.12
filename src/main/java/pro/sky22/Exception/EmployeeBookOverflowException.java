package pro.sky22.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeBookOverflowException extends RuntimeException {

    public EmployeeBookOverflowException(String bookIsFull) {
        super(bookIsFull);
    }
}
