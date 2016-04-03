package com.lastminute.flight.search.domain;

import java.time.LocalDate;
import java.util.List;

public class FlightSearch  {
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Integer numberOfInfants;
    private List<FlightDetail> flightDetails;

    public FlightSearch(String origin, String destination, LocalDate departureDate, Integer numberOfAdults, Integer numberOfChildren, Integer numberOfInfants) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.numberOfInfants = numberOfInfants;
    }

    public List<FlightDetail> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(List<FlightDetail> flightDetails) {
        this.flightDetails = flightDetails;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public Integer getNumberOfInfants() {
        return numberOfInfants;
    }
}
