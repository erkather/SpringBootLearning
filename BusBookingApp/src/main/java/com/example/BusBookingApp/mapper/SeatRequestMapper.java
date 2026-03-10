package com.example.BusBookingApp.mapper;

import com.example.BusBookingApp.dto.SeatRequest;
import com.example.BusBookingApp.entity.Seat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatRequestMapper {

    Seat toSeat(SeatRequest seatRequest);

    List<Seat> toSeat(List<SeatRequest> seatRequests);
}
