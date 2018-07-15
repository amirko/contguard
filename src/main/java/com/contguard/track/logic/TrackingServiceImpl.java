package com.contguard.track.logic;

import com.contguard.track.model.Coordinate;
import com.contguard.track.model.TelemtryDTO;
import com.contguard.track.model.TrackingDTO;
import com.contguard.track.model.VesselDTO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;


@Service
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    Comparator<Date> dateRangeComparator;

    @Override
    public List<TrackingDTO> matchTelemetriesWithVessels(List<TelemtryDTO> telemetries, List<VesselDTO> vessels) {
        Map<Coordinate, List<VesselDTO>> coordinateVesselDTOMap = retrieveVesselsByCoordinates(vessels);
        Map<Coordinate, List<TelemtryDTO>> coordinateTelemtryDTOMap = retrieveTelemetriesByCoordinates(telemetries);
        matchCoordinates(coordinateVesselDTOMap, coordinateTelemtryDTOMap);
        return matchDates(coordinateVesselDTOMap, coordinateTelemtryDTOMap);
    }

    private Map<Coordinate, List<TelemtryDTO>> retrieveTelemetriesByCoordinates(List<TelemtryDTO> telemetries) {
        return telemetries.stream().collect(groupingBy(t ->new Coordinate(t.getLongtitude(), t.getLatitude())));
    }

    private Map<Coordinate, List<VesselDTO>> retrieveVesselsByCoordinates(List<VesselDTO> vessels) {
        return vessels.stream().collect(groupingBy(v -> new Coordinate(v.getLongtitude(), v.getLatitude())));
    }

    private void matchCoordinates(Map<Coordinate, List<VesselDTO>> coordinateVesselDTOMap, Map<Coordinate, List<TelemtryDTO>> coordinateTelemtryDTOMap) {
        Set<Coordinate> commonCoordinates = coordinateTelemtryDTOMap.keySet();
        commonCoordinates.retainAll(coordinateVesselDTOMap.keySet());
        coordinateVesselDTOMap.keySet().retainAll(commonCoordinates);
    }

    private List<TrackingDTO> matchDates(Map<Coordinate, List<VesselDTO>> coordinateVesselDTOMap, Map<Coordinate, List<TelemtryDTO>> coordinateTelemtryDTOMap) {
        List<TrackingDTO> res = Lists.newArrayList();
        for (Map.Entry<Coordinate, List<VesselDTO>> vesselEntry : coordinateVesselDTOMap.entrySet()) {
            List<VesselDTO> vessels = vesselEntry.getValue();
            Map<Date, VesselDTO> dateToVessel = vessels.stream().collect(toMap(v -> v.getTime(), v -> v));
            List<Date> vesselDates = Lists.newArrayList(dateToVessel.keySet());
            vesselDates.sort(Date::compareTo);
            List<TelemtryDTO> telemetries = coordinateTelemtryDTOMap.get(vesselEntry.getKey()); // can't be null, since keys were unified
            Map<Date, TelemtryDTO> dateToTelemetry = telemetries.stream().collect(toMap(t -> t.getReceived(), t -> t));
            List<Date> telemetryDates = Lists.newArrayList(dateToTelemetry.keySet());
            for (Date telemetryDate : telemetryDates) {
                int index = Collections.<Date>binarySearch(vesselDates, telemetryDate, dateRangeComparator);
                if(index >= 0) { // match
                    res.add(createTrackingDTO(dateToTelemetry.get(telemetryDate), dateToVessel.get(vesselDates.get(index))));
                }
            }
        }
        return res;
    }

    private TrackingDTO createTrackingDTO(TelemtryDTO telemtryDTO, VesselDTO vesselDTO) {
        TrackingDTO trackingDTO = new TrackingDTO();
        BeanUtils.copyProperties(telemtryDTO, trackingDTO);
        trackingDTO.setMmsi(vesselDTO.getMmsi());
        return trackingDTO;
    }


}
