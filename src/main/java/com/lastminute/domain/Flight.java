package com.lastminute.domain;

import java.io.Serializable;

public class Flight implements Serializable {
    private String origin;
    private String destination;
    private String airline;
    private Double basePrice;

    public Flight() {
    }

    public Flight(String origin, String destination, String airline, Double basePrice) {
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.basePrice = basePrice;
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
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
