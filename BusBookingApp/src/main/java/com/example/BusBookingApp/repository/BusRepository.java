package com.example.BusBookingApp.repository;

import com.example.BusBookingApp.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    @Query("SELECT b FROM Bus b WHERE b.busNumber = ?1")
    Optional<Bus> findByBusNumber(String busNumber);

    @Modifying
    @Transactional
    @Query("DELETE FROM Bus b WHERE b.busNumber = ?1")
    void deleteByBusNumber(String busNumber);
}
