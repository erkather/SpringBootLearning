package com.example.BusBookingApp.dto;

import java.time.LocalDate;

public record UserResponse(String name, String fullname, String email, String role, LocalDate dob) {
}
