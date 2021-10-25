package com.league.exam.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class ExceptionDto {

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;

}
