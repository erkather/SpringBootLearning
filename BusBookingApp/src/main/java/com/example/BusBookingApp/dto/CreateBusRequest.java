package com.example.BusBookingApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateBusRequest(

        @NotBlank(message = "Bus number is required")
        @JsonProperty("bus_number")
        String busNumber,

        @NotBlank(message = "Bus type is required")
        String type,

        @NotBlank(message = "Manufacturer is required")
        String manufacturer,

        @NotBlank(message = "Bus operator is required")
        @JsonProperty("bus_operator")
        String busOperator,
        List<SeatRequest> seats) {

}
