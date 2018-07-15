package com.contguard.track.dao.filter;

import com.opencsv.bean.CsvToBeanFilter;
import org.junit.Test;

import static org.junit.Assert.*;

public class GpsFilterTest {

    private GpsFilter gpsFilter = new GpsFilter();

    @Test
    public void allowLine_whenValid() {
        String[] line = new String[]{"7/17/2017 8:01", "7/17/2017 8:02", "reason", "100", "10", "90", "..." + GpsFilter.VALID, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
        assertTrue(gpsFilter.allowLine(line));
    }

    @Test
    public void allowLineFalse_whenNotValid() {
        String[] line = new String[]{"7/17/2017 8:01", "7/17/2017 8:02", "reason", "100", "10", "90", "...", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
        assertFalse(gpsFilter.allowLine(line));
    }


    @Test
    public void allowLineFalse_whenNull() {
        String[] line = new String[]{"7/17/2017 8:01", "7/17/2017 8:02", "reason", "100", "10", "90", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
        assertFalse(gpsFilter.allowLine(line));
    }
}