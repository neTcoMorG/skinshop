package mc.jun.skinshop.web.controller.handler;

import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.exception.NullTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TokenExceptionHandler {

    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    @ExceptionHandler(NullTokenException.class)
    public void nullTokenExceptionHandler (NullTokenException ex) {
        log.info("[Token] 요청에 토큰이 없습니다");
    }
}
