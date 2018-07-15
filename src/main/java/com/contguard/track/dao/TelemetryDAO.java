package com.contguard.track.dao;

import com.contguard.track.model.TelemtryDTO;
import com.opencsv.bean.CsvToBeanFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TelemetryDAO extends AbstractDAO<TelemtryDTO> {

    @Autowired
    CsvToBeanFilter gpsFilter;

    @Override
    protected Class getTargetClass() {
        return TelemtryDTO.class;
    }

    @Override
    protected CsvToBeanFilter getFilter() {
        return gpsFilter;
    }
}
