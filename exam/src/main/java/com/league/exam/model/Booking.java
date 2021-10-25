package com.league.exam.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "bookings")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends Identifiable {
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "booking_created_at")
    private LocalDateTime bookingCreatedAt;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @JoinColumn(name = "person")
    @ManyToOne
    private User user;

}
