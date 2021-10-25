package com.league.exam.configuration.exception;

public class BookingConfirmationTimeExpired extends RuntimeException{
    public BookingConfirmationTimeExpired(String message) {
        super(message);
    }

}
