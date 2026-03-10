package com.example.BusBookingApp.specification;

import com.example.BusBookingApp.entity.BusRoute;
import com.example.BusBookingApp.entity.BusRouteBoardingPoint;
import com.example.BusBookingApp.entity.BusRouteDroppingPoint;
import com.example.BusBookingApp.entity.BusRouteServiceDays;
import com.example.BusBookingApp.entity.ServiceDays;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public final class BusRouteSpecification {

    private BusRouteSpecification()
    {
        // Private constructor to prevent instantiation
    }

    public static Specification<BusRoute> getBusRoutesBySourceDestinationAndDay(long sourceId, long destinationId, ServiceDays serviceDay)
    {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);

            Join<BusRoute, BusRouteBoardingPoint> boardingPointJoin = root.join("boardingPoints", JoinType.INNER);
            Join<BusRoute, BusRouteDroppingPoint> droppingPointJoin = root.join("droppingPoints", JoinType.INNER);
            Join<BusRoute, BusRouteServiceDays> serviceDaysJoin = root.join("serviceDays", JoinType.INNER);

            Predicate sourcePredicate = criteriaBuilder.equal(boardingPointJoin.get("id"), sourceId);
            Predicate destinationPredicate = criteriaBuilder.equal(droppingPointJoin.get("id"), destinationId);
            Predicate serviceDaysPredicate = criteriaBuilder.equal(serviceDaysJoin.get(serviceDay.getName()), true);

            return criteriaBuilder.and(sourcePredicate, destinationPredicate, serviceDaysPredicate);

        };

    }
}
