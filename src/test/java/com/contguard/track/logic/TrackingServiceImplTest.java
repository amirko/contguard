package com.contguard.track.logic;

import com.contguard.track.logic.comparators.DateRangeComparator;
import com.contguard.track.model.TelemtryDTO;
import com.contguard.track.model.TrackingDTO;
import com.contguard.track.model.VesselDTO;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TrackingServiceImplTest {

    private TrackingServiceImpl trackingService = new TrackingServiceImpl();
    private Comparator<Date> dateRangeComparator = new DateRangeComparator(15);

    @Before
    public void init() {
        trackingService.dateRangeComparator = dateRangeComparator;
    }

    @Test
    public void matchTelemetriesWithVessels() {
        VesselDTO vesselDTO0 = VesselDTO.builder()
                                        .mmsi(1)
                                        .longtitude(1.0)
                                        .latitude(1.0)
                                        .time(Date.from(LocalDateTime.parse("07/20/2017 08:00", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .build();
        VesselDTO vesselDTO1 = VesselDTO.builder()
                                        .mmsi(2)
                                        .longtitude(1.0)
                                        .latitude(1.0)
                                        .time(Date.from(LocalDateTime.parse("07/20/2017 08:20", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .build();
        VesselDTO vesselDTO2 = VesselDTO.builder()
                                        .mmsi(2)
                                        .longtitude(2.0)
                                        .latitude(2.0)
                                        .time(Date.from(LocalDateTime.parse("07/21/2017 08:00", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .build();
        VesselDTO vesselDTO3 = VesselDTO.builder()
                                        .mmsi(1)
                                        .longtitude(2.0)
                                        .latitude(3.0)
                                        .time(Date.from(LocalDateTime.parse("07/21/2017 08:00", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .build();
        List<VesselDTO> vesselDTOs = Lists.newArrayList(vesselDTO0, vesselDTO1, vesselDTO2, vesselDTO3);
        TelemtryDTO telemtryDTO0 = TelemtryDTO.builder()
                                        .longtitude(1.0)
                                        .latitude(1.0)
                                        .received(Date.from(LocalDateTime.parse("07/20/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .gps("/Valid")
                                        .name(0)
                                        .build();
        TelemtryDTO telemtryDTO1 = TelemtryDTO.builder()
                                        .longtitude(1.0)
                                        .latitude(1.0)
                                        .received(Date.from(LocalDateTime.parse("07/20/2017 09:00", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .gps("/Valid")
                                        .name(1)
                                        .build();
        TelemtryDTO telemtryDTO2 = TelemtryDTO.builder()
                                        .longtitude(2.0)
                                        .latitude(2.0)
                                        .received(Date.from(LocalDateTime.parse("07/21/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .gps("/Valid")
                                        .name(2)
                                        .build();
        TelemtryDTO telemtryDTO3 = TelemtryDTO.builder()
                                        .longtitude(2.0)
                                        .latitude(2.0)
                                        .received(Date.from(LocalDateTime.parse("07/20/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .gps("/Valid")
                                        .name(3)
                                        .build();
        TelemtryDTO telemtryDTO4 = TelemtryDTO.builder()
                                        .longtitude(4.0)
                                        .latitude(1.0)
                                        .received(Date.from(LocalDateTime.parse("07/20/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                        .gps("/Valid")
                                        .name(4)
                                        .build();
        List<TelemtryDTO> telemtryDTOs = Lists.newArrayList(telemtryDTO0, telemtryDTO1, telemtryDTO2, telemtryDTO3,telemtryDTO4);

        TrackingDTO trackingDTO0 = new TrackingDTO(); // there is a problem implementing inheritance with builder in lombok
        trackingDTO0.setMmsi(1);
        trackingDTO0.setLongtitude(1.0);
        trackingDTO0.setLatitude(1.0);
        trackingDTO0.setReceived(Date.from(LocalDateTime.parse("07/20/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())));
        trackingDTO0.setGps("/Valid");
        trackingDTO0.setName(0);

        TrackingDTO trackingDTO1 = new TrackingDTO();
        trackingDTO1.setMmsi(2);
        trackingDTO1.setLongtitude(2.0);
        trackingDTO1.setLatitude(2.0);
        trackingDTO1.setReceived(Date.from(LocalDateTime.parse("07/21/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())));
        trackingDTO1.setGps("/Valid");
        trackingDTO1.setName(2);
        List<TrackingDTO> expected = Lists.newArrayList(trackingDTO1, trackingDTO0);

        List<TrackingDTO> actual = trackingService.matchTelemetriesWithVessels(telemtryDTOs, vesselDTOs);
        assertEquals(expected, actual);
    }
}