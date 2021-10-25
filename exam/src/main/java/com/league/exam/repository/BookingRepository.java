package com.league.exam.repository;

import com.league.exam.model.Booking;
import com.league.exam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    Optional<Booking> findByDateTime(LocalDateTime timeSlot);

    Booking getByDateTime(LocalDateTime timeSlot);

    List<Booking> findByUserAndDateTimeAfter(User user, LocalDateTime localDateTime);

    List<Booking> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Query("SELECT b FROM Booking b WHERE b.dateTime = (SELECT MIN(b.dateTime) FROM Booking b WHERE b.dateTime > ?1 and status not like 'COMPLETED')")
    Booking getNextAppointment(LocalDateTime nowMinusTenMinutes);
}
