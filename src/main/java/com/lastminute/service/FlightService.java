package com.lastminute.service;

import com.lastminute.domain.FlightSearch;

import java.util.Date;
import java.util.List;

public interface FlightService {
    FlightSearch findFlights(FlightSearch flightSearch);
}
