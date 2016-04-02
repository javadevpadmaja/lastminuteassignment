package com.lastminute.service;

import com.lastminute.domain.Flight;
import com.lastminute.domain.FlightSearch;
import com.lastminute.domain.FlightDetail;
import com.lastminute.exceptions.InvalidDepartureDateException;
import com.lastminute.exceptions.InvalidOriginAndDestinationException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.lastminute.service.PassengerType.*;
import static com.lastminute.service.Util.loadFlights;
import static com.lastminute.service.Util.round;
import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toList;

public class FlightServiceImpl implements FlightService {
    private static Map<String, List<Flight>> flightsMap;

    public FlightServiceImpl() {
        flightsMap = unmodifiableMap(loadFlights("flights.csv"));
    }

    @Override
    public FlightSearch findFlights(FlightSearch flightSearch) {
        List<Flight> flights = getFlights(flightSearch.getOrigin(), flightSearch.getDestination());
        flightSearch.setFlightDetails(flights.stream().map(FlightDetail::new).collect(toList()));
        return calculateFares(flightSearch);
    }

    private List<Flight> getFlights(String origin, String destination) throws InvalidOriginAndDestinationException {
        String key = origin.concat(destination);
        if (flightsMap.containsKey(key)) {
            return flightsMap.get(key);
        }
        throw new InvalidOriginAndDestinationException("no flights available");
    }

    private FlightSearch calculateFares(FlightSearch flightSearch) {

        long daysFromDepartureDate = daysFromDepartureDate(flightSearch.getDepartureDate());
        flightSearch.getFlightDetails().stream()
                .forEach(flightDetail -> {
                    double percentByDaysToDepartureDate = percentByDaysToDepartureDate(daysFromDepartureDate);
                    flightDetail.setPercentByDaysToDepartureDate(percentByDaysToDepartureDate);
                    if (isExists(flightSearch.getNumberOfAdults()))
                        calculateFarePerPassenger(flightDetail, percentByDaysToDepartureDate, ADULT);
                    if (isExists(flightSearch.getNumberOfChildren()))
                        calculateFarePerPassenger(flightDetail, percentByDaysToDepartureDate, CHILD);
                    if(isExists(flightSearch.getNumberOfInfants())){
                        calculateInfantFare(flightDetail);
                    }
                    totalPriceOfAllPassengers(flightDetail, flightSearch.getNumberOfAdults(),
                            flightSearch.getNumberOfChildren(), flightSearch.getNumberOfInfants());
                });

        return flightSearch;

    }

    private void calculateInfantFare(FlightDetail flightDetail) {
        String airlineCode = flightDetail.getFlight().getAirline().substring(0,2).toUpperCase();
        flightDetail.setPricePerInfant(InfantPrices.valueOf(airlineCode).getValue());
    }

    private void calculateFarePerPassenger(FlightDetail flightDetail, Double percentByDaysToDepartureDate, PassengerType passengerType) {
        switch (passengerType) {
            case ADULT:
                flightDetail.setPercentOfDiscountPerAdultPassenger(percentByPassengerType(passengerType));
                Double pricePerAdult = calculateFare(flightDetail.getFlight().getBasePrice(), flightDetail.getPercentOfDiscountPerAdultPassenger(), percentByDaysToDepartureDate);
                flightDetail.setPricePerAdult(pricePerAdult);
                break;
            case CHILD:
                flightDetail.setPercentOfDiscountPerChildPassenger(percentByPassengerType(passengerType));
                Double pricePerChild = calculateFare(flightDetail.getFlight().getBasePrice(), flightDetail.getPercentOfDiscountPerChildPassenger(), percentByDaysToDepartureDate);
                flightDetail.setPricePerChild(pricePerChild);
        }
    }

    private boolean isExists(Integer noOfPassengers) {
        return noOfPassengers != null && noOfPassengers > 0;
    }

    private Double calculateFare(Double basePrice, Double discountByPassengerType, double priceByDepartureDate) {
        Double fareByDepartureDate = (priceByDepartureDate * basePrice) / 100;
        Double fare = (discountByPassengerType * fareByDepartureDate) / 100;
        return fare;
    }

    private long daysFromDepartureDate(LocalDate departureDate) {
        LocalDate currentDate = LocalDate.now();
        long numberOfDays = ChronoUnit.DAYS.between(currentDate, departureDate);
        if (numberOfDays < 0) {
            throw new InvalidDepartureDateException("Departure date should not be past date");
        }
        return numberOfDays;
    }

    private double percentByDaysToDepartureDate(long daysToDepartureDate) {
        if (daysToDepartureDate < 3)
            return 150;
        else if (daysToDepartureDate >= 3 && daysToDepartureDate <= 15)
            return 120;
        else if (daysToDepartureDate >= 16 && daysToDepartureDate <= 30)
            return 100;
        else
            return 80;
    }

    private double percentByPassengerType(PassengerType passengerType) {
        int childDiscount = 33;
        switch (passengerType) {
            case ADULT:
                return 100;
            default:
                return 100 - childDiscount;
        }
    }

    private void totalPriceOfAllPassengers(FlightDetail flightDetail , Integer numberOfAdults, Integer numberOfChild, Integer numberOfInfants) {
        double totalPrice = 0;

        if (isExists(numberOfAdults)) {
            totalPrice += numberOfAdults * flightDetail.getPricePerAdult();
        }
        if(isExists(numberOfChild)) {
            totalPrice += numberOfChild * flightDetail.getPricePerChild();
        }
        if(isExists(numberOfInfants)) {
            totalPrice += numberOfInfants * flightDetail.getPricePerInfant();
        }
        flightDetail.setTotalPriceOfAllPassengers(round(totalPrice));
    }
}
