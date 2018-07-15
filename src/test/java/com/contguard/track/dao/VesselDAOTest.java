package com.contguard.track.dao;

import com.contguard.track.model.VesselDTO;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VesselDAOTest {

    VesselDAO vesselDao = new VesselDAO();

    @Test
    public void readVesselDTOs() throws URISyntaxException {
        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("vessels-v1.0.csv"));
        List<VesselDTO> actualVessels = vesselDao.fetchAll(reader);
//        List<VesselDTO> actualVessels = vesselDao.fetchAll(Paths.get(getClass().getClassLoader().getResource("vessels-v1.0.csv").toURI()).toFile().getAbsolutePath());
        VesselDTO vesselDTO0 = VesselDTO.builder()
                                    .mmsi(264162572)
                                    .time(Date.from(LocalDateTime.parse("07/20/2017 08:01", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                    .longtitude(8.553)
                                    .latitude(53.5822)
                                    .build();
        VesselDTO vesselDTO1 = VesselDTO.builder()
                                   .mmsi(244670509)
                                   .time(Date.from(LocalDateTime.parse("07/20/2017 10:05", DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm")).toInstant(OffsetDateTime.now().getOffset())))
                                   .longtitude(9.273)
                                   .latitude(48.749)
                                   .build();
        List<VesselDTO> expected = Lists.newArrayList(vesselDTO0, vesselDTO1);
        assertEquals(expected, actualVessels);
    }

}