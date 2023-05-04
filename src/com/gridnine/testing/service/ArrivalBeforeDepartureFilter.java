package com.gridnine.testing.service;

import com.gridnine.testing.models.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр "исключает те рейсы, которые имеются сегменты с датой прилёта раньше даты вылета"
 */
public class ArrivalBeforeDepartureFilter implements FlightFilter{
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
