package com.example.BusBookingApp.config;

import com.example.BusBookingApp.entity.BusOperator;
import com.example.BusBookingApp.entity.UserInfo;
import com.example.BusBookingApp.repository.BusOperatorRepository;
import com.example.BusBookingApp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

@Configuration
@Profile("dev")
public class UserConfiguration {

    @Bean
    @Order(1)
    CommandLineRunner userCommandLineRunner(UserRepository userRepository, BusOperatorRepository busOperatorRepository)
    {
        return args -> {
            System.out.println("Initializing users and bus operators...");
            UserInfo parveenOperatorUser = new UserInfo("ParveenAdmin", "Parveen Travels Admin", "operator@parveen.com", "bus_operator", "parveen123", LocalDate.parse("1990-01-01"));
            BusOperator parveenOperator = new BusOperator(parveenOperatorUser, "Parveen Travels",
                    "9876543210", "Parveen Travels, Chennai.");
            busOperatorRepository.save(parveenOperator);

            UserInfo sbltOperatorUser = new UserInfo("SBLTAdmin", "SBLT Travels Admin", "admin@sblt.com", "bus_operator", "sblt123", LocalDate.parse("1992-02-02"));
            BusOperator sbltOperator = new BusOperator(sbltOperatorUser, "SBLT Roadways",
                    "8765432109", "SBLT Roadways, Bangalore.");
            busOperatorRepository.save(sbltOperator);
        };
    }
}
