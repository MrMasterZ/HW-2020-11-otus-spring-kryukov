package otus.student.kryukov.dz.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value= RuntimeException.class)
    public String emptyEntityError(Model model, RuntimeException ex) {
        model.addAttribute("errMessage", "ERROR: " + ex.getMessage());
        return "book/error";
    }
}
