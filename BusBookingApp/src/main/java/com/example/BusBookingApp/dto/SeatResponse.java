package com.example.BusBookingApp.dto;

public record SeatResponse(Long id,
                           String seat_number,
                           String type,
                           String bus_id) {}
