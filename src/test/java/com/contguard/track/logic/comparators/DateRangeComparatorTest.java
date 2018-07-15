package com.contguard.track.logic.comparators;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.*;

public class DateRangeComparatorTest {

    private DateRangeComparator dateRangeComparator = new DateRangeComparator(15);

    @Test
    public void returnsZero_diffLE15() {
        Date date0 = Date.from(LocalDateTime.parse("07/20/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset()));
        Date date1 = Date.from(LocalDateTime.parse("07/20/2017 08:14", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset()));
        assertEquals(0, dateRangeComparator.compare(date0, date1));
        assertEquals(0, dateRangeComparator.compare(date1, date0));
    }

    @Test
    public void returnsNonZero_diffGE15() {
        Date date0 = Date.from(LocalDateTime.parse("07/20/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset()));
        Date date1 = Date.from(LocalDateTime.parse("07/20/2017 08:17", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset()));
        assertTrue(dateRangeComparator.compare(date0, date1) < 0);
        assertTrue(dateRangeComparator.compare(date1, date0) > 0);
    }

}