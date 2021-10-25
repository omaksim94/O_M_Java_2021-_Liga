package com.league.exam.controller;

import com.league.exam.model.dto.BookingDto;
import com.league.exam.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/admin/bookings")
@RequiredArgsConstructor
public class BookingAdminController {

    private final BookingService bookingService;

    @GetMapping("/next")
    public BookingDto getNextAppointment() {
        return bookingService.getNextAppointment();
    }

    @PutMapping
    public void changeBookingStatus(@RequestBody Map<String, String> json) {
        bookingService.changeBookingStatus(json);
    }

}
