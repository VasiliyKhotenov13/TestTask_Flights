package com.gridnine.testing.service;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр, который "фильтрует список рейсов и оставляет только те,
 * у которых общее время, проведенное на земле между сегментами, превышает 2 часа"
 */
public class TwoHoursPlusOnGroundFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() <= 1) {
                        return false;
                    }
                    int totalGroundTime = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        Duration groundTime = Duration.between(segments.get(i).getArrivalDate(),
                                segments.get(i + 1).getDepartureDate());
                        totalGroundTime += groundTime.toHours();
                    }
                    return totalGroundTime > 2;
                }).collect(Collectors.toList());
    }
}
