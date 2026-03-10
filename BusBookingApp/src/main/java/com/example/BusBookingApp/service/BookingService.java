package com.example.BusBookingApp.service;

import com.example.BusBookingApp.dto.BusResponse;
import com.example.BusBookingApp.dto.PageResponse;
import com.example.BusBookingApp.entity.Bus;
import com.example.BusBookingApp.entity.BusRoute;
import com.example.BusBookingApp.entity.BusStop;
import com.example.BusBookingApp.entity.ServiceDays;
import com.example.BusBookingApp.mapper.BusResponseMapper;
import com.example.BusBookingApp.repository.BusRouteRepository;
import com.example.BusBookingApp.repository.BusStopRepository;
import com.example.BusBookingApp.specification.BusRouteSpecification;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private BusRouteRepository busRouteRepository;
    private BusStopRepository busStopRepository;
    private BusResponseMapper busResponseMapper;


    @Autowired
    public BookingService(BusRouteRepository busRouteRepository, BusStopRepository busStopRepository, BusResponseMapper busResponseMapper)
    {
        this.busRouteRepository = busRouteRepository;
        this.busStopRepository = busStopRepository;
        this.busResponseMapper = busResponseMapper;
    }

    @Transactional(readOnly = true)
    public PageResponse<BusResponse> getBusRoutes(String source, String destination, Instant time, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        ServiceDays serviceDays = getServiceDays(time);
        BusStop sourcePoint = getBusStop(source);
        BusStop destinationPoint = getBusStop(destination);
        Specification<BusRoute> busRoutesBySourceDestinationAndDay =
                BusRouteSpecification.getBusRoutesBySourceDestinationAndDay(sourcePoint.getId(),
                        destinationPoint.getId(), serviceDays);
        Page<BusRoute> busRoutes = busRouteRepository.findAll(busRoutesBySourceDestinationAndDay, pageable);
        List<Bus> availableBuses = busRoutes.getContent().stream().map(BusRoute::getBus).collect(Collectors.toList());

        List<BusResponse> busResponseList = busResponseMapper.toBusResponse(availableBuses);
        return new PageResponse<>(busResponseList, busRoutes.getNumber(), busRoutes.getSize(),
                busRoutes.getTotalElements(), busRoutes.getTotalPages());
    }

    private @NonNull BusStop getBusStop(String stopName)
    {
        Optional<BusStop> sourcePoint = busStopRepository.findByName(stopName);
        if (sourcePoint.isEmpty()) {
            throw new IllegalArgumentException("Bus stop " + stopName + " not found");
        }
        return sourcePoint.get();
    }

    private static @Nullable ServiceDays getServiceDays(Instant time)
    {
        ZonedDateTime zonedDateTime = time.atZone(ZoneId.systemDefault());
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
        String day = dayOfWeek.name();
        return ServiceDays.fromName(day);
    }
}
