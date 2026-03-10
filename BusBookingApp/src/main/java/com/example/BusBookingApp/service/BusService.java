package com.example.BusBookingApp.service;

import com.example.BusBookingApp.dto.*;
import com.example.BusBookingApp.entity.Bus;
import com.example.BusBookingApp.entity.BusOperator;
import com.example.BusBookingApp.mapper.BusRequestMapper;
import com.example.BusBookingApp.mapper.BusResponseMapper;
import com.example.BusBookingApp.repository.BusOperatorRepository;
import com.example.BusBookingApp.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    private final BusRepository busRepository;
    private final BusResponseMapper responseMapper;
    private final BusRequestMapper requestMapper;
    private final BusOperatorRepository busOperatorRepository;

    @Autowired
    public BusService(BusRepository busRepository, BusResponseMapper responseMapper, BusRequestMapper requestMapper, BusOperatorRepository busOperatorRepository)
    {
        this.busRepository = busRepository;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
        this.busOperatorRepository = busOperatorRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<BusResponse> getAllBuses(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bus> busPage = busRepository.findAll(pageable);
        List<BusResponse> content = responseMapper.toBusResponse(busPage.getContent());
        return new PageResponse<BusResponse>(content, busPage.getNumber(), busPage.getSize(),
                busPage.getTotalElements(), busPage.getTotalPages());
    }

    @Transactional
    public BusResponse addNewBus(CreateBusRequest busRequest)
    {
        if (busRepository.findByBusNumber(busRequest.busNumber()).isPresent()) {
            throw new IllegalStateException("Bus with bus number " + busRequest.busNumber() + " already exists");
        }
        Optional<BusOperator> busOperator = busOperatorRepository.findByCompanyName(busRequest.busOperator());
        if (busOperator.isEmpty()) {
            throw new IllegalStateException("Bus operator with company name " + busRequest.busOperator() + " does not exist");
        }
        Bus bus = requestMapper.toBus(busRequest);
        bus.setBusOperator(busOperator.get());
        Bus newBus = busRepository.save(bus);
        return responseMapper.toBusResponse(newBus);
    }

    @Transactional
    public BusResponse updateBusDetails(String busNumber, UpdateBusRequest busRequest)
    {
        Bus bus = getBus(busNumber);
        if (busRequest.type() != null && !busRequest.type().isBlank() && !busRequest.type().equals(bus.getType())) {
            bus.setType(busRequest.type());
        }
        if (busRequest.manufacturer() != null && !busRequest.manufacturer().isBlank() && !busRequest.manufacturer().equals(bus.getManufacturer())) {
            bus.setManufacturer(busRequest.manufacturer());
        }
        return responseMapper.toBusResponse(bus);
    }

    @Transactional
    public void deleteBus(String busNumber)
    {
        Bus bus = getBus(busNumber);
        busRepository.delete(bus);
    }

    @Transactional(readOnly = true)
    public BusResponseIncludingSeats getBusByNumber(String busNumber)
    {
        Bus bus = getBus(busNumber);
        return responseMapper.toBusResponseIncludingSeats(bus);
    }

    private Bus getBus(String busNumber)
    {
        Optional<Bus> bus = busRepository.findByBusNumber(busNumber);
        if (bus.isEmpty()) {
            throw new IllegalStateException("Bus with bus number " + busNumber + " does not exist");
        }
        return bus.get();
    }
}
