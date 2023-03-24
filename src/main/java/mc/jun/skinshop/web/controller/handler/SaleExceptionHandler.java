package mc.jun.skinshop.web.controller.handler;

import lombok.extern.slf4j.Slf4j;
import mc.jun.skinshop.domain.exception.SizeOverflowException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SaleExceptionHandler {

    @ExceptionHandler(SizeOverflowException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void sizeOverflowExceptionHandler (RuntimeException ex) {
        log.info(ex.getMessage());
    }
}
