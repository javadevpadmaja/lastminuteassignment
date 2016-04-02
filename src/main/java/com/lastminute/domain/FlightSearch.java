package com.lastminute.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class FlightSearch implements Serializable {
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Integer numberOfInfants;
    private List<FlightDetail> flightDetails;

    public FlightSearch() {
    }

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

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Integer getNumberOfInfants() {
        return numberOfInfants;
    }

    public void setNumberOfInfants(Integer numberOfInfants) {
        this.numberOfInfants = numberOfInfants;
    }
}
