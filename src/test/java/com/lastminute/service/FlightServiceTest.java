package com.lastminute.service;

import com.lastminute.domain.FlightSearch;
import com.lastminute.exceptions.InvalidDepartureDateException;
import com.lastminute.exceptions.InvalidOriginAndDestinationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.time.LocalDate.now;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class FlightServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private FlightService flightService;

    @Before
    public void setUp() throws Exception {
        flightService = new FlightServiceImpl();
    }

    @Test
    public void returnFlightDetailsWithPrices_givenValidOriginAndDestinationForOneAdult_withMoreThan30Days_priorToDepartureDate() {
        //1 adult, 30 days to the departure date, flying AMS -> FRA
        FlightSearch flightSearch = new FlightSearch("AMS", "FRA", now().plusDays(31), 1, 0, 0);
        FlightSearch  flightSearchDetails = flightService.findFlights(flightSearch);

        assertNotNull(flightSearchDetails.getFlightDetails());
        assertThat(flightSearchDetails.getFlightDetails().size(), is(3));

        assertThat(flightSearchDetails.getFlightDetails().get(0).getTotalPriceOfAllPassengers(), is(157.6));
        assertThat(flightSearchDetails.getFlightDetails().get(0).getFlight().getAirline(), is("TK2372"));

        assertThat(flightSearchDetails.getFlightDetails().get(1).getTotalPriceOfAllPassengers(), is(198.4));
        assertThat(flightSearchDetails.getFlightDetails().get(1).getFlight().getAirline(), is("TK2659"));

        assertThat(flightSearchDetails.getFlightDetails().get(2).getTotalPriceOfAllPassengers(), is(90.4));
        assertThat(flightSearchDetails.getFlightDetails().get(2).getFlight().getAirline(), is("LH5909"));
    }

    @Test
    public void returnFlightDetailsWithPrices_givenValidOriginAndDestinationForTwoAdultsOneChildOneInfant_with15Days_priorToDepartureDate() {
        //2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST
        FlightSearch flightSearch = new FlightSearch("LHR", "IST", now().plusDays(15), 2, 1, 1);
        FlightSearch  flightSearchDetails = flightService.findFlights(flightSearch);

        assertNotNull(flightSearchDetails.getFlightDetails());
        assertThat(flightSearchDetails.getFlightDetails().size(), is(2));

        assertThat(flightSearchDetails.getFlightDetails().get(0).getTotalPriceOfAllPassengers(), is(806.0));
        assertThat(flightSearchDetails.getFlightDetails().get(0).getFlight().getAirline(), is("TK8891"));

        assertThat(flightSearchDetails.getFlightDetails().get(1).getTotalPriceOfAllPassengers(), is(481.19));
        assertThat(flightSearchDetails.getFlightDetails().get(1).getFlight().getAirline(), is("LH1085"));
    }

    @Test
    public void returnFlightDetailsWithPrices_givenValidOriginAndDestinationForOneAdultTwoChildren_with2Days_priorToDepartureDate() {
        //1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD
        FlightSearch flightSearch = new FlightSearch("BCN", "MAD", now().plusDays(2), 1, 2, 0);
        FlightSearch  flightSearchDetails = flightService.findFlights(flightSearch);

        assertNotNull(flightSearchDetails.getFlightDetails());
        assertThat(flightSearchDetails.getFlightDetails().size(), is(2));

        assertThat(flightSearchDetails.getFlightDetails().get(0).getTotalPriceOfAllPassengers(), is(909.09));
        assertThat(flightSearchDetails.getFlightDetails().get(0).getFlight().getAirline(), is("IB2171"));

        assertThat(flightSearchDetails.getFlightDetails().get(1).getTotalPriceOfAllPassengers(), is(1028.43));
        assertThat(flightSearchDetails.getFlightDetails().get(1).getFlight().getAirline(), is("LH5496"));
    }

    @Test(expected = InvalidOriginAndDestinationException.class)
    public void shouldThrowExceptionGivenInvalidOriginAndDestination() throws Exception {
        FlightSearch flightSearch = new FlightSearch("CDG", "FRA", now().plusDays(30), 1,0,0);
        flightService.findFlights(flightSearch);
        thrown.expect(InvalidOriginAndDestinationException.class);
        thrown.expectMessage("no flights available");
    }

    @Test
    public void returnFlightDetailsWithPrices_givenValidOriginAndDestinationForOneAdult_withLessThan3Days_priorToDepartureDate() {
        //1 adult, 2 days to the departure date, flying AMS -> FRA
        FlightSearch flightSearch = new FlightSearch("AMS", "FRA", now().plusDays(2), 1, 0, 0);
        FlightSearch  flightSearchDetails = flightService.findFlights(flightSearch);

        assertNotNull(flightSearchDetails.getFlightDetails());
        assertThat(flightSearchDetails.getFlightDetails().size(), is(3));

        assertThat(flightSearchDetails.getFlightDetails().get(0).getTotalPriceOfAllPassengers(), is(295.5));
        assertThat(flightSearchDetails.getFlightDetails().get(0).getFlight().getAirline(), is("TK2372"));

        assertThat(flightSearchDetails.getFlightDetails().get(1).getTotalPriceOfAllPassengers(), is(372.0));
        assertThat(flightSearchDetails.getFlightDetails().get(1).getFlight().getAirline(), is("TK2659"));

        assertThat(flightSearchDetails.getFlightDetails().get(2).getTotalPriceOfAllPassengers(), is(169.5));
        assertThat(flightSearchDetails.getFlightDetails().get(2).getFlight().getAirline(), is("LH5909"));
    }

    @Test
    public void returnFlightDetailsWithPrices_givenValidOriginAndDestinationForOneAdultAndInfant_withMoreThan15Days_priorToDepartureDate() {
        //1 adult, 17 days to the departure date, flying AMS -> FRA
        FlightSearch flightSearch = new FlightSearch("AMS", "FRA", now().plusDays(16), 1, 0, 1);
        FlightSearch  flightSearchDetails = flightService.findFlights(flightSearch);

        assertNotNull(flightSearchDetails.getFlightDetails());
        assertThat(flightSearchDetails.getFlightDetails().size(), is(3));

        assertThat(flightSearchDetails.getFlightDetails().get(0).getTotalPriceOfAllPassengers(), is(202.0));
        assertThat(flightSearchDetails.getFlightDetails().get(0).getFlight().getAirline(), is("TK2372"));

        assertThat(flightSearchDetails.getFlightDetails().get(1).getTotalPriceOfAllPassengers(), is(253.0));
        assertThat(flightSearchDetails.getFlightDetails().get(1).getFlight().getAirline(), is("TK2659"));

        assertThat(flightSearchDetails.getFlightDetails().get(2).getTotalPriceOfAllPassengers(), is(120.0));
        assertThat(flightSearchDetails.getFlightDetails().get(2).getFlight().getAirline(), is("LH5909"));
    }

    @Test(expected = InvalidDepartureDateException.class)
    public void shouldThrowExceptionGivenInvalidDepartureDate() throws Exception {
        //given
        FlightSearch flightSearch = new FlightSearch("AMS", "FRA", now().minusDays(30), 1,0,0);
        FlightSearch  flightSearchDetails =flightService.findFlights(flightSearch);
        thrown.expect(InvalidDepartureDateException.class);
        thrown.expectMessage("Departure date should not be past date");
    }
}
