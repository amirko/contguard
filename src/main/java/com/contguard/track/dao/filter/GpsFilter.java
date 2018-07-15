package com.contguard.track.dao.filter;

import com.opencsv.bean.CsvToBeanFilter;
import org.springframework.stereotype.Component;

@Component
public class GpsFilter implements CsvToBeanFilter {

    private static final int GPS_COLUMN = 6; // in real life we would have to calculate this index for each submission
    static final String VALID = "/ Valid";



    @Override
    public boolean allowLine(String[] tokens) {
        if(tokens[GPS_COLUMN] != null && tokens[GPS_COLUMN].contains(VALID)) {
            return true;
        }
        return false;
    }
}
