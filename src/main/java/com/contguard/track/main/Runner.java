package com.contguard.track.main;

import com.contguard.track.dao.TelemetryDAO;
import com.contguard.track.dao.VesselDAO;
import com.contguard.track.logic.TrackingService;
import com.contguard.track.model.TelemtryDTO;
import com.contguard.track.model.TrackingDTO;
import com.contguard.track.model.VesselDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class Runner {

    @Autowired
    private TelemetryDAO telemetryDAO;
    @Autowired
    private VesselDAO vesselDAO;
    @Autowired
    private TrackingService trackingService;
    @Autowired
    private Gson gson;
    @Autowired
    private Persister persister;

    public void run(String telemetriesFile, String vesselsFile, String outputFile) {
        try {
            List<TelemtryDTO> telemetries = telemetryDAO.fetchAll(new FileReader(telemetriesFile));
            List<VesselDTO> vessels = vesselDAO.fetchAll(new FileReader(vesselsFile));
            List<TrackingDTO> result = trackingService.matchTelemetriesWithVessels(telemetries, vessels);
            String res = gson.toJson(result);
            System.out.println(res);
            persister.persist(outputFile, res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
