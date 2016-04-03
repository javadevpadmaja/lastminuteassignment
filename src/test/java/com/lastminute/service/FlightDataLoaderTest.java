package com.lastminute.service;

import org.hamcrest.core.IsNot;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertThat;

public class FlightDataLoaderTest {
    @Test
    public void shouldLoadFlightsFromCSV() throws Exception {
        FlightDataLoader flightDataLoader = FlightDataLoader.INSTANCE;
        assertThat(flightDataLoader.getFlightsMap(), IsNot.not(Collections.EMPTY_MAP));
    }
}