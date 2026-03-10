package com.example.BusBookingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table
public class BusRouteBoardingPoint {

    @Id
    @SequenceGenerator(
            name = "bus_route_boarding_point_sequence",
            sequenceName = "bus_route_boarding_point_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE,
            generator = "bus_route_boarding_point_sequence"
    )
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_route_id", referencedColumnName = "id", nullable = false)
    private BusRoute busRoute;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_stop_id", referencedColumnName = "id", nullable = false)
    private BusStop boardingPoint;

    private int stopOrder;

    private LocalTime boardingTime;

    public BusRouteBoardingPoint()
    {

    }

    public BusRouteBoardingPoint(BusStop boardingPoint, int stopOrder, LocalTime boardingTime)
    {
        this.boardingPoint = boardingPoint;
        this.stopOrder = stopOrder;
        this.boardingTime = boardingTime;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BusRoute getBusRoute()
    {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute)
    {
        this.busRoute = busRoute;
    }

    public BusStop getBoardingPoint()
    {
        return boardingPoint;
    }

    public void setBoardingPoint(BusStop boardingPoint)
    {
        this.boardingPoint = boardingPoint;
    }

    public int getStopOrder()
    {
        return stopOrder;
    }

    public void setStopOrder(int stopOrder)
    {
        this.stopOrder = stopOrder;
    }

    public LocalTime getBoardingTime()
    {
        return boardingTime;
    }

    public void setBoardingTime(LocalTime boardingTime)
    {
        this.boardingTime = boardingTime;
    }
}
