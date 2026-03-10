package com.example.BusBookingApp.config;


import com.example.BusBookingApp.entity.Bus;
import com.example.BusBookingApp.entity.BusRoute;
import com.example.BusBookingApp.entity.BusRouteBoardingPoint;
import com.example.BusBookingApp.entity.BusRouteDroppingPoint;
import com.example.BusBookingApp.entity.BusRouteServiceDays;
import com.example.BusBookingApp.entity.BusStop;
import com.example.BusBookingApp.entity.Seat;
import com.example.BusBookingApp.repository.BusOperatorRepository;
import com.example.BusBookingApp.repository.BusRepository;
import com.example.BusBookingApp.repository.BusRouteRepository;
import com.example.BusBookingApp.repository.BusStopRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

@Configuration
@Profile("dev")
public class BusConfiguration {

    @Bean
    @Order(2)
    CommandLineRunner busStopCommandLineRunner(BusStopRepository busStopRepository)
    {
        return args -> {
            System.out.println("Initializing bus stops...");
            BusStop chennaiStop = new BusStop("Chennai", "Koyambedu");
            BusStop tenkasiStop = new BusStop("Tenkasi", "Tenkasi Bus Stand");
            BusStop kadayanallurStop = new BusStop("Kadayanallur", "Tenkasi Bus Stand");
            BusStop bengaloreStop = new BusStop("Bengalore", "Majestic");

            busStopRepository.saveAll(List.of(chennaiStop, tenkasiStop, kadayanallurStop, bengaloreStop));
        };
    }

    @Bean
    @Order(3)
    CommandLineRunner busRouteCommandLineRunner(BusRepository busRepository, BusOperatorRepository busOperatorRepository,
                                        BusStopRepository busStopRepository, BusRouteRepository busRouteRepository)
    {
        return args -> {

            System.out.println("Initializing buses and bus routes...");

            busOperatorRepository.findByCompanyName("Parveen Travels").ifPresent(operator -> {
                // Initialization logic can be added here
                Bus parveenTravels = new Bus("TN76AA1234", "Seater", "Mercedes-Benz", operator);
                parveenTravels.addSeat(new Seat("A1", "Seater"));
                parveenTravels.addSeat(new Seat("A2", "Seater"));
                parveenTravels.addSeat(new Seat("B1", "Seater"));
                busRepository.saveAll(List.of(parveenTravels));

                BusRoute parveenBusRoute = new BusRoute(parveenTravels, Instant.now(), ZonedDateTime.now().plusYears(10).toInstant());
                BusRouteServiceDays serviceDays = new BusRouteServiceDays(true, true, true, true, true, false, false);
                parveenBusRoute.setServiceDays(serviceDays);

                busStopRepository.findByName("Chennai").ifPresent(chennaiStop -> {
                    // Add boarding point
                    parveenBusRoute.addBoardingPoint(new BusRouteBoardingPoint(chennaiStop, 1, LocalTime.of(18, 30)));
                });

                busStopRepository.findByName("Kadayanallur").ifPresent(kadayanallurStop -> {
                    // Add dropping point
                    parveenBusRoute.addDroppingPoint(new BusRouteDroppingPoint(kadayanallurStop, 1, LocalTime.of(6, 30)));
                });

                busStopRepository.findByName("Tenkasi").ifPresent(tenkasiStop -> {
                    // Add dropping point
                    parveenBusRoute.addDroppingPoint(new BusRouteDroppingPoint(tenkasiStop, 2, LocalTime.of(7, 0)));
                });

                busRouteRepository.save(parveenBusRoute);
            });

            busOperatorRepository.findByCompanyName("SBLT Roadways").ifPresent(operator -> {
                // Initialization logic can be added here
                Bus sbltRoadways = new Bus("KA01BB5678", "Sleeper", "Volvo", operator);
                sbltRoadways.addSeat(new Seat("S1", "Sleeper"));
                sbltRoadways.addSeat(new Seat("S2", "Sleeper"));
                sbltRoadways.addSeat(new Seat("S3", "Sleeper"));
                busRepository.saveAll(List.of(sbltRoadways));

                BusRoute sbltBusRoute = new BusRoute(sbltRoadways, Instant.now(), ZonedDateTime.now().plusYears(10).toInstant());
                BusRouteServiceDays serviceDays = new BusRouteServiceDays(true, true, true, true, true, true, false);
                sbltBusRoute.setServiceDays(serviceDays);

                busStopRepository.findByName("Bengalore").ifPresent(bengaloreStop -> {
                    // Add boarding point
                    sbltBusRoute.addBoardingPoint(new BusRouteBoardingPoint(bengaloreStop, 1, LocalTime.of(20, 0)));
                });
                busStopRepository.findByName("Chennai").ifPresent(chennaiStop -> {
                    // Add dropping point
                    sbltBusRoute.addDroppingPoint(new BusRouteDroppingPoint(chennaiStop, 1, LocalTime.of(6, 0)));
                });
                busRouteRepository.save(sbltBusRoute);
            });

        };
    }
}
