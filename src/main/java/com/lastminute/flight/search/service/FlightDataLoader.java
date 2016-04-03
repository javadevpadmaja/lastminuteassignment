package com.lastminute.flight.search.service;

import com.lastminute.flight.search.domain.Flight;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.lang.Double.parseDouble;
import static java.util.Collections.unmodifiableMap;

public enum FlightDataLoader {
    INSTANCE;
    private String LINE_SEPARATOR = ",";
    private  Map<String, List<Flight>> flightsMap;

    public  Map<String, List<Flight>> getFlightsMap() {
        return flightsMap;
    }

     FlightDataLoader() {
        flightsMap = unmodifiableMap(loadFlights("flights.csv"));
    }

    private Map<String, List<Flight>> loadFlights(String fileName)  {
        Map<String, List<Flight>> flightsMap = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSystemResourceAsStream(fileName)))) {
            bufferedReader.lines()
                    .forEach(line -> {
                        String[] flightDetails = line.split(LINE_SEPARATOR);
                        String key = flightDetails[0] + flightDetails[1];
                        Flight flight = new Flight(flightDetails[0], flightDetails[1],
                                flightDetails[2], parseDouble(flightDetails[3]));
                        List<Flight> flights;
                        if(flightsMap.containsKey(key)){
                            flights = flightsMap.get(key);
                        } else {
                            flights = new ArrayList<>();
                        }
                        flights.add(flight);
                        flightsMap.put(key,flights);
                    });
        } catch (Exception ex){
            throw new IllegalArgumentException("File not available");
        }
        return flightsMap;
    }

    public List<Flight> getFlights(String origin, String destination) {

        return flightsMap.get(origin.concat(destination));
    }

}
