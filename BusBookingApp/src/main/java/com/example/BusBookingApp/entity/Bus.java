package com.example.BusBookingApp.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Bus {

    @Id
    @SequenceGenerator(
            name = "bus_sequence",
            sequenceName = "bus_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = jakarta.persistence.GenerationType.SEQUENCE,
            generator = "bus_sequence"
    )
    private Long id;
    @Column(unique = true, nullable = false)
    private String busNumber;
    private String type;
    private String manufacturer;

    @ManyToOne()
    @JoinColumn(name = "operator_id", referencedColumnName = "operator_id", nullable = false)
    private BusOperator busOperator;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    // Constructors

    public Bus()
    {
        // Mapper requires a default constructor.
        // Also mapper can copy the list values from source object to target only if the list is initialized.
        seats = new ArrayList<>();
    }

    public Bus(String busNumber, String type, String manufacturer, BusOperator busOperator)
    {
        this.busNumber = busNumber;
        this.type = type;
        this.manufacturer = manufacturer;
        this.busOperator = busOperator;
        this.seats = new ArrayList<>();
    }

    public void addSeat(Seat seat)
    {
        seat.setBus(this);
        seats.add(seat);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getBusNumber()
    {
        return busNumber;
    }

    public void setBusNumber(String busNumber)
    {
        this.busNumber = busNumber;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public BusOperator getBusOperator()
    {
        return busOperator;
    }

    public void setBusOperator(BusOperator busOperator)
    {
        this.busOperator = busOperator;
    }

    public List<Seat> getSeats()
    {
        return seats;
    }
}
