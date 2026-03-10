package com.example.BusBookingApp.repository;

import com.example.BusBookingApp.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Long>, JpaSpecificationExecutor<BusRoute> {
}
