package com.league.exam.configuration.exception;

import com.league.exam.model.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BookingConfirmationTimeExpired.class, RuntimeException.class})
    public ResponseEntity<Object> exceptionHandlerOne(RuntimeException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionDto, badRequest);
    }


}