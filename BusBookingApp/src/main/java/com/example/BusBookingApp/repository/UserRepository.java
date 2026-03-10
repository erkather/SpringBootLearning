package com.example.BusBookingApp.repository;

import com.example.BusBookingApp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    @Query("SELECT u FROM UserInfo u WHERE u.name = ?1")
    Optional<UserInfo> findByUsername(String username);

    @Query("SELECT u FROM UserInfo u WHERE u.email = ?1")
    Optional<UserInfo> findByEmail(String email);

}
