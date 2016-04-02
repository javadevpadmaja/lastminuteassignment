package com.lastminute.domain;

import java.io.Serializable;

public class FlightDetail implements Serializable {

    private Flight flight;
    private Double percentByDaysToDepartureDate;
    private Double percentOfDiscountPerAdultPassenger;
    private Double percentOfDiscountPerChildPassenger;
    private Double percentOfDiscountPerInfantPassenger;
    private Double pricePerAdult;
    private Double pricePerChild;
    private Double pricePerInfant;
    private Double totalPriceOfAllPassengers;

    public FlightDetail() {
    }

    public FlightDetail(Flight flight) {
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Double getPercentByDaysToDepartureDate() {
        return percentByDaysToDepartureDate;
    }

    public void setPercentByDaysToDepartureDate(Double percentByDaysToDepartureDate) {
        this.percentByDaysToDepartureDate = percentByDaysToDepartureDate;
    }

    public Double getPercentOfDiscountPerAdultPassenger() {
        return percentOfDiscountPerAdultPassenger;
    }

    public void setPercentOfDiscountPerAdultPassenger(Double percentOfDiscountPerAdultPassenger) {
        this.percentOfDiscountPerAdultPassenger = percentOfDiscountPerAdultPassenger;
    }

    public Double getPercentOfDiscountPerChildPassenger() {
        return percentOfDiscountPerChildPassenger;
    }

    public void setPercentOfDiscountPerChildPassenger(Double percentOfDiscountPerChildPassenger) {
        this.percentOfDiscountPerChildPassenger = percentOfDiscountPerChildPassenger;
    }

    public Double getPercentOfDiscountPerInfantPassenger() {
        return percentOfDiscountPerInfantPassenger;
    }

    public void setPercentOfDiscountPerInfantPassenger(Double percentOfDiscountPerInfantPassenger) {
        this.percentOfDiscountPerInfantPassenger = percentOfDiscountPerInfantPassenger;
    }

    public Double getPricePerAdult() {
        return pricePerAdult;
    }

    public void setPricePerAdult(Double pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }

    public Double getPricePerChild() {
        return pricePerChild;
    }

    public void setPricePerChild(Double pricePerChild) {
        this.pricePerChild = pricePerChild;
    }

    public Double getPricePerInfant() {
        return pricePerInfant;
    }

    public void setPricePerInfant(Double pricePerInfant) {
        this.pricePerInfant = pricePerInfant;
    }

    public Double getTotalPriceOfAllPassengers() {
        return totalPriceOfAllPassengers;
    }

    public void setTotalPriceOfAllPassengers(Double totalPriceOfAllPassengers) {
        this.totalPriceOfAllPassengers = totalPriceOfAllPassengers;
    }

    @Override
    public String toString() {
        return "FlightDetail{" +
                "flight=" + flight +
                ", percentByDaysToDepartureDate = " + percentByDaysToDepartureDate +
                ", percentOfDiscountPerAdultPassenger = " + percentOfDiscountPerAdultPassenger + "% " +
                ", percentOfDiscountPerChildPassenger = " + percentOfDiscountPerChildPassenger +"% " +
                ", percentOfDiscountPerInfantPassenger=" + percentOfDiscountPerInfantPassenger + "% " +
                ", pricePerAdult = " + pricePerAdult + " €" +
                ", pricePerChild = " + pricePerChild + " €" +
                ", pricePerInfant = " + pricePerInfant + " €" +
                ", totalPriceOfAllPassengers = " + totalPriceOfAllPassengers +
                '}';
    }
}
