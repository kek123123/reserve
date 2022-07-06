package com.shnam.reserve.config.exception;

import com.pcn.fiss.config.exception.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponseDto illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] e", e);
        return new ErrorResponseDto("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponseDto noSuchExHandler(NoSuchElementException e) {
        log.error("[exceptionHandler] e", e);
        return new ErrorResponseDto("400", e.getMessage());
    }
}
