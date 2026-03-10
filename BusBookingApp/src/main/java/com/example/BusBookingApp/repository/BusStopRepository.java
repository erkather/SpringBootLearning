package com.example.BusBookingApp.repository;

import com.example.BusBookingApp.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Long> {

    @Query("SELECT b FROM BusStop b WHERE b.name = ?1")
    Optional<BusStop> findByName(String city);
}
