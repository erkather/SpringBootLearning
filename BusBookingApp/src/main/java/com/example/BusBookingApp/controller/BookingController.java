package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.dto.BusResponse;
import com.example.BusBookingApp.dto.PageResponse;
import com.example.BusBookingApp.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping(path = "api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }

    @GetMapping("/buses")
    public ResponseEntity<PageResponse<BusResponse>> getBusesForBooking(@RequestParam String source, @RequestParam String destination, @RequestParam(name = "date", required = false) String travelDate,
                                                                        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        Instant travelInstant = stringToInstant(travelDate);
        PageResponse<BusResponse> busRoutes = bookingService.getBusRoutes(source, destination, travelInstant, page, size);
        return ResponseEntity.ok(busRoutes);
    }

    /**
     * Converts a date string in "DD-MM-YYYY" format to Instant at start of day in system default zone.
     */
    private Instant stringToInstant(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return Instant.now();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate localDate = LocalDate.parse(dateString, formatter);
            return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeParseException e) {
            // fallback to now if parsing fails
            return Instant.now();
        }
    }
}
