package com.league.exam.service;

import com.league.exam.configuration.Schedule;
import com.league.exam.configuration.exception.BookingConfirmationTimeExpired;
import com.league.exam.model.Booking;
import com.league.exam.model.BookingStatus;
import com.league.exam.model.User;
import com.league.exam.model.dto.BookingDto;
import com.league.exam.repository.BookingRepository;
import com.league.exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.league.exam.model.BookingStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@EnableScheduling
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final Schedule schedule;
    private final DateTimeFormatter dateTimeFormatter;

    public List<BookingDto> getFreeBookingSlots() {

        List<BookingDto> freeBookingSlots = new ArrayList<>();

        LocalDateTime nextAppointmentDateTime = LocalDateTime.of(LocalDate.now(), schedule.getOpeningHour());
        while (!nextAppointmentDateTime.equals(LocalDateTime.of(LocalDate.now().plusDays(7), schedule.getOpeningHour()))) {

            freeBookingSlots.add(new BookingDto(nextAppointmentDateTime, FREE));
            nextAppointmentDateTime = nextAppointmentDateTime.plusMinutes(30);

            if (nextAppointmentDateTime.getHour() == schedule.getClosingHour().getHour()) {
                nextAppointmentDateTime = nextAppointmentDateTime
                        .plusDays(1)
                        .minusHours(schedule.getClosingHour().getHour() - schedule.getOpeningHour().getHour());
            }
        }

        List<LocalDateTime> bookings =
                bookingRepository.findByDateTimeBetween(LocalDateTime.now(), LocalDateTime.now().plusDays(7)).stream()
                        .map(Booking::getDateTime)
                        .collect(Collectors.toList());

        return freeBookingSlots = freeBookingSlots.stream()
                .filter(slot -> !bookings.contains(slot.getDateTime()))
                .sorted()
                .collect(Collectors.toList());

    }

    @Transactional
    public void bookAppointment(Map<String, String> json) {

        LocalDateTime timeSlot = dateTimeFormatter.parse(json.get("dateTime"), LocalDateTime::from);

        if (timeSlot.compareTo(LocalDateTime.now()) <= 0) {
            throw new IllegalStateException("Booking for " + timeSlot + " unavailable." +
                    "Please choose another time slot.");
        }
        Optional<Booking> optionalBooking = bookingRepository.findByDateTime(timeSlot);

        Booking newBooking = new Booking(
                timeSlot,
                LocalDateTime.now(),
                ACCEPTED,
                getUserFromSecurityContext());

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if (isBookingConfirmationTimeExpired(booking)) {

                System.out.println("Please click on link below to confirm your appointment at " + timeSlot
                        + " \n localhost:8080/api/v1/bookings/confirm-booking/" + timeSlot.format(dateTimeFormatter));
                bookingRepository.save(newBooking);
                return;
            } else {
                throw new IllegalStateException("This time slot has already been booked. " +
                        "Please choose different timeslot for appointment");
            }
        }

        System.out.println("Please click on link below to confirm your appointment at " + timeSlot
                + " \n localhost:8080/api/v1/bookings/confirm-booking/" + timeSlot.format(dateTimeFormatter));
        bookingRepository.save(newBooking);
    }

    private User getUserFromSecurityContext() {
        String senderEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByEmail(senderEmail)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
    }

    private boolean isBookingConfirmationTimeExpired(Booking booking) {
        return booking.getStatus().equals(ACCEPTED) &&
                Duration.between(booking.getBookingCreatedAt(), LocalTime.now()).toMinutes() >= 15;
    }

    @Transactional(noRollbackFor = BookingConfirmationTimeExpired.class)
    public void confirmAppointment(String timeSlotString) {
        LocalDateTime timeSlot = dateTimeFormatter.parse(timeSlotString, LocalDateTime::from);
        Booking booking = bookingRepository.findByDateTime(timeSlot)
                .orElseThrow(() -> new EntityNotFoundException("Booking for " + timeSlot + " doesn't exist"));

        if (Duration.between(booking.getBookingCreatedAt(), LocalDateTime.now()).toMinutes() < 15) {
            if (booking.getStatus().equals(CONFIRMED)) {
                throw new IllegalStateException("Booking has already been confirmed for " + timeSlot);
            }
            booking.setStatus(CONFIRMED);
            bookingRepository.save(booking);
        } else {
            bookingRepository.delete(booking);
            throw new BookingConfirmationTimeExpired("Booking hasn't been confirmed in time and was cancelled. " +
                    "Please make a new booking appointment");
        }

    }

    public List<BookingDto> getUserActiveBookings() {
        return bookingRepository.findByUserAndDateTimeAfter(getUserFromSecurityContext(), LocalDateTime.now()).stream()
                .map(BookingDto::new)
                .sorted()
                .collect(Collectors.toList());
    }

    @Transactional
    public void changeBookingStatus(Map<String, String> json) {
        LocalDateTime timeSlot = dateTimeFormatter.parse(json.get("dateTime"), LocalDateTime::from);
        Booking booking = bookingRepository.getByDateTime(timeSlot);
        booking.setStatus(BookingStatus.valueOf(json.get("status")));
        bookingRepository.save(booking);

    }

    public BookingDto getNextAppointment() {
        return new BookingDto(bookingRepository.getNextAppointment(LocalDateTime.now().minusMinutes(10)));
    }

    @Scheduled(cron = "0 0 19 * * *")
    @Transactional
    public void updateStatusForNotConfirmedAndNotArrived() {
        List<Booking> notConfirmedBookings = bookingRepository.findByDateTimeBetween(
                LocalDateTime.of(LocalDate.now(), schedule.getOpeningHour()), LocalDateTime.now())
                .stream()
                .filter(booking -> booking.getStatus().equals(ACCEPTED) || booking.getStatus().equals(CONFIRMED))
                .peek(booking -> {
                    if (booking.getStatus().equals(ACCEPTED)) {
                        booking.setStatus(NOT_CONFIRMED);
                    } else {
                        booking.setStatus(USER_DIDNT_ARRIVE);
                    }
                })
                .collect(Collectors.toList());
        bookingRepository.saveAll(notConfirmedBookings);
    }
}
