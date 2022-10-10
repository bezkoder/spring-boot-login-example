package br.com.douglas.aterrosystem.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int status;
    private String message;
    private boolean validation;
    private String exception;
    private String stacktrace;

    public static ErrorResponse badRequest(String message) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build();
    }
}
