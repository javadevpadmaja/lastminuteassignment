package com.lastminute.flight.search.service;

import com.lastminute.flight.search.domain.FlightSearch;

public interface FlightService {
    FlightSearch findFlights(FlightSearch flightSearch);
}
