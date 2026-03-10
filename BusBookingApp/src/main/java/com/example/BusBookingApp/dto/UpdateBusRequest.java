package com.example.BusBookingApp.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateBusRequest(
        @NotBlank
        String type,

        @NotBlank
        String manufacturer

) {
}
