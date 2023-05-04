package com.gridnine.testing.service;

import com.gridnine.testing.models.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр "исключает те рейсы, вылет у которых до текущего момента времени"
 */
public class BeforeCurrentTimeFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime currentTime = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().get(0)
                        .getDepartureDate().isAfter(currentTime))
                .collect(Collectors.toList());
    }
}
