package pro.sky22.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundExceptiion extends RuntimeException {
    public EmployeeNotFoundExceptiion(String notFound) {
        super(notFound);
    }
}
