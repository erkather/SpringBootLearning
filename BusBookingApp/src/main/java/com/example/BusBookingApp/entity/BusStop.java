package com.example.BusBookingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class BusStop {

    @Id
    @SequenceGenerator(
            name = "bus_stop_sequence",
            sequenceName = "bus_stop_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = jakarta.persistence.GenerationType.SEQUENCE,
            generator = "bus_stop_sequence"
    )
    private Long id;
    private String name;
    private String location;

    public BusStop()
    {
    }

    public BusStop(String name, String location)
    {
        this.name = name;
        this.location = location;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
