package com.lastminute.service;

import com.lastminute.domain.Flight;
import com.lastminute.domain.FlightDetail;
import com.lastminute.domain.FlightSearch;
import com.lastminute.exceptions.InvalidDepartureDateException;
import com.lastminute.exceptions.InvalidOriginAndDestinationException;
import com.lastminute.exceptions.SameOriginAndDestinationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.lastminute.service.PassengerType.ADULT;
import static com.lastminute.service.PassengerType.CHILD;
import static java.util.stream.Collectors.toList;

public class FlightServiceImpl implements FlightService {

    private FlightDataLoader flightDataLoader = FlightDataLoader.INSTANCE;

    @Override
    public FlightSearch findFlights(FlightSearch flightSearch) {
        checkIfOriginAndDestinationAreSame(flightSearch.getOrigin(), flightSearch.getDestination());
        checkIfOriginAndDestinationAreValid(flightSearch.getOrigin(), flightSearch.getDestination());
        List<Flight> flights = flightDataLoader.getFlights(flightSearch.getOrigin(), flightSearch.getDestination());
        flightSearch.setFlightDetails(flights.stream().map(FlightDetail::new).collect(toList()));
        return calculateFares(flightSearch);
    }



    private void checkIfOriginAndDestinationAreSame(String origin, String destination) {
        if (origin.equals(destination)) {
            throw new SameOriginAndDestinationException("Origin and Destination are same");
        }
    }

    private void checkIfOriginAndDestinationAreValid(String origin, String destination) {
        if (!flightDataLoader.getFlightsMap().containsKey(origin.concat(destination))) {
            throw new InvalidOriginAndDestinationException("no flights available");
        }
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
        return (discountByPassengerType * fareByDepartureDate) / 100;
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
        else if (departureDateBetween(daysToDepartureDate, 3, 15))
            return 120;
        else if (departureDateBetween(daysToDepartureDate, 16, 30))
            return 100;
        else
            return 80;
    }

    private boolean departureDateBetween(long daysToDepartureDate, int start, int end) {
        return daysToDepartureDate >= start && daysToDepartureDate <= end;
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
        flightDetail.setTotalPriceOfAllPassengers(format(totalPrice));
    }

    protected static double format(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
