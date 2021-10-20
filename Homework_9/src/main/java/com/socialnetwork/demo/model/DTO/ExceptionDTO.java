package com.socialnetwork.demo.model.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class ExceptionDTO {

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;

}
