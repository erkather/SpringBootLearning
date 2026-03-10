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
public class BusRouteDroppingPoint {

    @Id
    @SequenceGenerator(
            name = "bus_route_dropping_point_sequence",
            sequenceName = "bus_route_dropping_point_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE,
            generator = "bus_route_dropping_point_sequence"
    )
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_route_id", referencedColumnName = "id", nullable = false)
    private BusRoute busRoute;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_stop_id", referencedColumnName = "id", nullable = false)
    private BusStop droppingPoint;

    private int stopOrder;

    private LocalTime droppingTime;

    public BusRouteDroppingPoint()
    {

    }

    public BusRouteDroppingPoint(BusStop droppingPoint, int stopOrder, LocalTime droppingTime)
    {
        this.busRoute = busRoute;
        this.droppingPoint = droppingPoint;
        this.stopOrder = stopOrder;
        this.droppingTime = droppingTime;
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

    public BusStop getDroppingPoint()
    {
        return droppingPoint;
    }

    public void setDroppingPoint(BusStop droppingPoint)
    {
        this.droppingPoint = droppingPoint;
    }

    public int getStopOrder()
    {
        return stopOrder;
    }

    public void setStopOrder(int stopOrder)
    {
        this.stopOrder = stopOrder;
    }

    public LocalTime getDroppingTime()
    {
        return droppingTime;
    }

    public void setDroppingTime(LocalTime droppingTime)
    {
        this.droppingTime = droppingTime;
    }
}
