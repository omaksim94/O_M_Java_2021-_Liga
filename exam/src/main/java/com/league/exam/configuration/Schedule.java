package com.league.exam.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Schedule {
    private LocalTime timeForAppointment = LocalTime.of(0, 30);
    private LocalTime openingHour = LocalTime.of(9, 0);
    private LocalTime closingHour = LocalTime.of(18, 0);
    private Set<LocalDate> holidays = new HashSet<>();

}
