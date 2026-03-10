package com.example.BusBookingApp.entity;

public enum ServiceDays {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");

    private final String name;
    private ServiceDays(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ServiceDays fromName(String day) {
        for (ServiceDays serviceDay : ServiceDays.values()) {
            if (serviceDay.getName().equalsIgnoreCase(day)) {
                return serviceDay;
            }
        }
        return null;
    }

}
