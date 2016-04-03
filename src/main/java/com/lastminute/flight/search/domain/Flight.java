package com.lastminute.flight.search.domain;

public class Flight {
    private String origin;
    private String destination;
    private String airline;
    private Double basePrice;

    public Flight(String origin, String destination, String airline, Double basePrice) {
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.basePrice = basePrice;
    }

    public String getAirline() {
        return airline;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "airline='" + airline + '\'' +
                ", " + origin + " -> "
                + destination
                + ", basePrice= " + basePrice + " €"
                + '}';
    }
}
