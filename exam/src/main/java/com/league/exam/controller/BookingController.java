package com.league.exam.controller;

import com.league.exam.model.dto.BookingDto;
import com.league.exam.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public List<BookingDto> getFreeBookingSlotsForNextWeek() {
        return bookingService.getFreeBookingSlots();
    }

    @GetMapping("/active")
    public List<BookingDto> getUserActiveBookings() {
        return bookingService.getUserActiveBookings();
    }

    @PostMapping
    public void bookAppointment(@RequestBody Map<String, String> json){
        bookingService.bookAppointment(json);
    }

    @PutMapping("/confirm-booking/{timeSlot}")
    public void confirmAppointment(@PathVariable String timeSlot){
        bookingService.confirmAppointment(timeSlot);
    }


}
