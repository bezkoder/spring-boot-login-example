package br.com.douglas.aterrosystem.exceptionHandler;

import br.com.douglas.aterrosystem.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler  extends ResponseEntityExceptionHandler {

    private static final String ERRO_NAO_ESPERADO = "Erro não esperado";

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.info(ERRO_NAO_ESPERADO, ex);
        return new ResponseEntity<>(ErrorResponse.badRequest(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDomainException(Exception ex, WebRequest request) {
        log.info(ERRO_NAO_ESPERADO, ex);
        return new ResponseEntity<>(ErrorResponse.badRequest(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
