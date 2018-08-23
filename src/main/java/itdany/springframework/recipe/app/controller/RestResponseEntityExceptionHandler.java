package itdany.springframework.recipe.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NumberFormatException.class})
    public String noHandlerFoundException(Exception ex) {
        // todo
        log.error(ex.getCause().toString());
        return "error while converting id";
    }

}
