package com.socialnetwork.demo.exception;

import com.socialnetwork.demo.model.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MyCustomException.class, EntityNotFoundException.class})
    public ResponseEntity<Object> exceptionHandlerOne(RuntimeException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionDTO, badRequest);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Object> exceptionHandlerTwo(RuntimeException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionDTO, badRequest);
    }

}