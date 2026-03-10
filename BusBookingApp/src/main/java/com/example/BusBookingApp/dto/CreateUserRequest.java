package com.example.BusBookingApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateUserRequest(
        @NotBlank(message = "Username should not be empty.")
        String name,

        String fullname,

        @NotBlank(message = "Email should not be empty.")
        String email,

        @NotBlank(message = "Password should not be empty.")
        String password,

        @NotNull(message = "Date of birth should not be empty.")
        LocalDate dob) {
}
