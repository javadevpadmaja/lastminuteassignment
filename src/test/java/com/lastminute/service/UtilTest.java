package com.lastminute.service;

import org.junit.Test;

import static java.util.Collections.emptyMap;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class UtilTest {
    @Test
    public void shouldLoadFlightsFromCSV() throws Exception {
        assertThat(Util.loadFlights("flights.csv"), not(emptyMap()));
    }
}