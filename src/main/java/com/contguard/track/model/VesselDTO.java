package com.contguard.track.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VesselDTO {

    @CsvBindByName(column = "Vessel (MMSI)")
    private Integer mmsi;
    @CsvBindByName(column = "Time")
    @CsvDate("M/dd/yyyy hh:mm")
    private Date time;
    @CsvBindByName(column = "Longitude")
    private Double longtitude;
    @CsvBindByName(column = "Latitude")
    private Double latitude;
}
