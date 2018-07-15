package com.contguard.track.dao;

import com.contguard.track.model.VesselDTO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    @Override
    public List<T> fetchAll(Reader reader) {
        try {
            CsvToBean csvTransfer = new CsvToBeanBuilder(reader)
                                            .withType(getTargetClass())
                                            .withFilter(getFilter())
                                            .build();
            List<T> res = csvTransfer.parse();
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {

            }
        }
    }

    protected CsvToBeanFilter getFilter() {
        return null;
    }

    protected abstract Class getTargetClass();

}
