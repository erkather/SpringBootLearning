package com.example.BusBookingApp.mapper;

import com.example.BusBookingApp.dto.SeatResponse;
import com.example.BusBookingApp.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatResponseMapper {

    @Mapping(source = "seatNumber", target = "seat_number")
    @Mapping(source = "bus.id", target = "bus_id")
    SeatResponse toResponse(Seat seat);

    List<SeatResponse> toResponse(List<Seat> seats);
}
