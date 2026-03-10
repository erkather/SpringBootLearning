package com.example.BusBookingApp.mapper;

import com.example.BusBookingApp.dto.BusResponse;
import com.example.BusBookingApp.dto.BusResponseIncludingSeats;
import com.example.BusBookingApp.entity.Bus;
import com.example.BusBookingApp.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
uses = {SeatResponseMapper.class})
public interface BusResponseMapper {

    @Mapping(source = "busNumber", target = "bus_number")
    @Mapping(source = "busOperator.companyName", target = "bus_operator")
    @Mapping(source = "seats", target = "capacity")
    BusResponse toBusResponse(Bus bus);

    List<BusResponse> toBusResponse(List<Bus> buses);

    default int mapSeatsToCapacity(List<Seat> seats) {
        return seats != null ? seats.size() : 0;
    }

    @Mapping(source = "busNumber", target = "bus_number")
    @Mapping(source = "busOperator.companyName", target = "bus_operator")
    BusResponseIncludingSeats toBusResponseIncludingSeats(Bus buses);

}
