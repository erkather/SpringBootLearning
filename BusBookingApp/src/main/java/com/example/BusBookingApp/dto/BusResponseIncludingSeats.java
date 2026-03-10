package com.example.BusBookingApp.dto;

import java.util.List;

public record BusResponseIncludingSeats(Long id,
                                        String bus_number,
                                        String type,
                                        String manufacturer,
                                        String bus_operator,
                                        List<SeatResponse> seats) {
}
