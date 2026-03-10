package com.example.BusBookingApp.dto;

public record BusResponse (Long id,
                           String bus_number,
                           String type,
                           String manufacturer,
                           String bus_operator,
                           int capacity) {}
