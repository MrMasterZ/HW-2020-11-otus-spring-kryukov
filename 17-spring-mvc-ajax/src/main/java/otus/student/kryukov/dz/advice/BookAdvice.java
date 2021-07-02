package otus.student.kryukov.dz.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookAdvice {
    @ExceptionHandler(RuntimeException.class)
    public String entityError(RuntimeException ex) {
        return "RuntimeException: " + ex.getMessage();
    }
}
