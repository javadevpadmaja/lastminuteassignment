package com.lastminute.service;

import com.lastminute.domain.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.lang.Double.parseDouble;

public class Util {
    static String LINE_SEPARATOR = ",";
    private static final Logger logger = LogManager.getLogger(Util.class);

    public static Map<String, List<Flight>> loadFlights(String fileName) {
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
            logger.error(ex);
        }
        return flightsMap;
    }

    protected static double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
