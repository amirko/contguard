package com.contguard.track.logic;

import com.contguard.track.model.TelemtryDTO;
import com.contguard.track.model.TrackingDTO;
import com.contguard.track.model.VesselDTO;

import java.util.List;

public interface TrackingService {
    List<TrackingDTO> matchTelemetriesWithVessels(List<TelemtryDTO> telemetries, List<VesselDTO> vessels);
}
