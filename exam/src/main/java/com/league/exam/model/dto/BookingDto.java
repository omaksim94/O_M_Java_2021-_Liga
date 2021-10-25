package com.league.exam.model.dto;

import com.league.exam.model.Booking;
import com.league.exam.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto implements Comparable<BookingDto> {

    private LocalDateTime dateTime;
    private BookingStatus status;

    public BookingDto(Booking booking) {
        this.dateTime = booking.getDateTime();
        this.status = booking.getStatus();
    }

    @Override
    public int compareTo(BookingDto o) {
        return this.getDateTime().compareTo(o.getDateTime());
    }
}
