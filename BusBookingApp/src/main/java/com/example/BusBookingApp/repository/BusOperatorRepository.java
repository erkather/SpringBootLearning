package com.example.BusBookingApp.repository;

import com.example.BusBookingApp.entity.BusOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusOperatorRepository extends JpaRepository<BusOperator, Long> {

    @Query("SELECT b FROM BusOperator b WHERE b.companyName = ?1")
    Optional<BusOperator> findByCompanyName(String companyName);
}
