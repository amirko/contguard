package com.contguard.track.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelemtryDTO {

    @CsvBindByName(column = "Received")
    @CsvDate("M/dd/yyyy hh:mm")
    protected Date received;
    @CsvBindByName(column = "Last location", required = false)
    @CsvDate("M/dd/yyyy hh:mm")
    protected Date lastLocation;
    @CsvBindByName(column = "Reason", required = false)
    protected String reason;
    @CsvBindByName(column = "Speed (kph)", required = false)
    protected Integer speed;
    @CsvBindByName(column = "Mileage (km)", required = false)
    protected Integer mileage;
    @CsvBindByName(column = "Heading", required = false)
    protected Integer heading;
    @CsvBindByName(column = "GPS", required = false)
    protected String gps;
    @CsvBindByName(column = "Installed", required = false)
    protected String installed;
    @CsvBindByName(column = "Button", required = false)
    protected String button;
    @CsvBindByName(column = "Door", required = false)
    protected String door;
    @CsvBindByName(column = "Main power", required = false)
    protected String mainPower;
    @CsvBindByName(column = "Light", required = false)
    protected Integer light;
    @CsvBindByName(column = "Temperature", required = false)
    protected String temperature;
    @CsvBindByName(column = "Address")
    protected String address;
    @CsvBindByName(column = "Longitude")
    protected Double longtitude;
    @CsvBindByName(column = "Latitude")
    protected Double latitude;
    @CsvBindByName(column = "Altitude (m)", required = false)
    protected Integer altitude;
    @CsvBindByName(column = "Name")
    protected Integer name;
}
