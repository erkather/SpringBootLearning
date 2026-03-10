package com.example.BusBookingApp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class BusRoute {

    @Id
    @SequenceGenerator(
            name = "bus_route_sequence",
            sequenceName = "bus_route_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = jakarta.persistence.GenerationType.SEQUENCE,
            generator = "bus_route_sequence"
    )
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_id", referencedColumnName = "id", nullable = false)
    private Bus bus;

    private Instant startDate;

    private Instant endDate;

    @OneToOne(mappedBy = "busRoute", cascade = CascadeType.ALL, orphanRemoval = true)
    private BusRouteServiceDays serviceDays;

    @OneToMany(mappedBy = "busRoute", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusRouteBoardingPoint> boardingPoints;

    @OneToMany(mappedBy = "busRoute", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusRouteDroppingPoint> droppingPoints;


    public BusRoute() {
        this.boardingPoints = new ArrayList<>();
        this.droppingPoints = new ArrayList<>();
    }

    public BusRoute(Bus bus, Instant startDate, Instant endDate) {
        this.bus = bus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.boardingPoints = new ArrayList<>();
        this.droppingPoints = new ArrayList<>();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Bus getBus()
    {
        return bus;
    }

    public void setBus(Bus bus)
    {
        this.bus = bus;
    }

    public Instant getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Instant startDate)
    {
        this.startDate = startDate;
    }

    public Instant getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Instant endDate)
    {
        this.endDate = endDate;
    }

    public void setServiceDays(BusRouteServiceDays serviceDays)
    {
        serviceDays.setBusRoute(this);
        this.serviceDays = serviceDays;
    }

    public void addBoardingPoint(BusRouteBoardingPoint boardingPoint)
    {
        boardingPoint.setBusRoute(this);
        boardingPoints.add(boardingPoint);
    }

    public void addDroppingPoint(BusRouteDroppingPoint droppingPoint)
    {
        droppingPoint.setBusRoute(this);
        droppingPoints.add(droppingPoint);
    }
}
