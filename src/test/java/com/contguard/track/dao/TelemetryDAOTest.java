package com.contguard.track.dao;

import com.contguard.track.dao.filter.GpsFilter;
import com.contguard.track.model.TelemtryDTO;
import com.contguard.track.model.VesselDTO;
import com.opencsv.bean.CsvToBeanFilter;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TelemetryDAOTest {

    private TelemetryDAO telemetryDAO;

    @Before
    public void init() {
        CsvToBeanFilter gpsFilter = new GpsFilter();
        telemetryDAO = new TelemetryDAO();
        telemetryDAO.gpsFilter = gpsFilter;
    }

    @Test
    public void readTelemetryDTOs() throws URISyntaxException {
        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("telemetries.csv"));
        List<TelemtryDTO> actualTelemetries = telemetryDAO.fetchAll(reader);
        TelemtryDTO telemtryDTO = TelemtryDTO.builder()
                                          .received(Date.from(LocalDateTime.parse("07/12/2017 08:22", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                          .lastLocation(Date.from(LocalDateTime.parse("07/11/2017 20:15", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                          .reason("Tracking")
                                          .speed(10)
                                          .mileage(0)
                                          .heading(0)
                                          .gps("3 / Off / Valid")
                                          .installed("Off")
                                          .button("Off")
                                          .door("Opened")
                                          .mainPower("64% / 3.92v")
                                          .light(1)
                                          .temperature("29.0c")
                                          .address("Mettingen (Customer Daimler Power Train: Daimler AG (Stuttgart, Germany))")
                                          .longtitude(9.2723)
                                          .latitude(48.7481)
                                          .altitude(243)
                                          .name(591016)
                                          .build();
        List<TelemtryDTO> expected = Lists.newArrayList(telemtryDTO);
        assertEquals(expected, actualTelemetries);
    }

}