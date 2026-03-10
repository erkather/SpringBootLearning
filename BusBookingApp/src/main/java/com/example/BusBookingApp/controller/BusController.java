package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.dto.*;
import com.example.BusBookingApp.service.BusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/buses")
public class BusController {


    private final BusService busService;

    @Autowired
    public BusController(BusService busService)
    {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<BusResponse>> getAllBuses(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(busService.getAllBuses(page, size));
    }

    @GetMapping("/{busNumber}")
    public ResponseEntity<BusResponseIncludingSeats> getBus(@PathVariable String busNumber)
    {
        BusResponseIncludingSeats busByNumber = busService.getBusByNumber(busNumber);
        return ResponseEntity.ok(busByNumber);
    }

    @PostMapping
    public ResponseEntity<BusResponse> registerNewBus(@Valid @RequestBody CreateBusRequest busRequest)
    {
        // Implementation for registering a new bus
        BusResponse busResponse = busService.addNewBus(busRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(busResponse);
    }

    @PutMapping("/{busNumber}")
    public ResponseEntity<BusResponse> updateBusDetails(@PathVariable String busNumber, @Valid @RequestBody UpdateBusRequest busRequest)
    {
        BusResponse busResponse = busService.updateBusDetails(busNumber, busRequest);
        return ResponseEntity.ok(busResponse);
    }

    @DeleteMapping("/{busNumber}")
    public void deleteBus(@PathVariable String busNumber)
    {
        busService.deleteBus(busNumber);
    }
}
