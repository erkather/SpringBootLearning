package com.example.BusBookingApp.mapper;

import com.example.BusBookingApp.dto.CreateBusRequest;
import com.example.BusBookingApp.entity.Bus;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {SeatRequestMapper.class})
public abstract class BusRequestMapper {

    @Autowired
    protected SeatRequestMapper seatRequestMapper;

    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "busOperator", ignore = true)
    public abstract Bus toBus(CreateBusRequest busRequest);

    @AfterMapping
    protected void addSeats(CreateBusRequest busRequest, @MappingTarget Bus bus)
    {
        if (busRequest.seats() != null) {
            busRequest.seats().stream().map(seatRequestMapper::toSeat).forEach(bus::addSeat);
        }
    }
}
