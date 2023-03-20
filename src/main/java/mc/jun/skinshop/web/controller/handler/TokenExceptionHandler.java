package mc.jun.skinshop.web.controller.handler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.exception.NullTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class TokenExceptionHandler {

    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    @ExceptionHandler(NullTokenException.class)
    public void nullTokenExceptionHandler (NullTokenException ex, HttpServletResponse response) throws IOException {
        log.info("[Token] " + ex.getMessage());
    }
}
