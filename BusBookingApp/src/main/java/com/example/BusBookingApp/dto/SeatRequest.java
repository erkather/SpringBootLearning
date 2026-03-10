package com.example.BusBookingApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SeatRequest(@JsonProperty("seat_number") String seatNumber,
                          String type) {
}
