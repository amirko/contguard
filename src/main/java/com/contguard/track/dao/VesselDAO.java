package com.contguard.track.dao;

import com.contguard.track.model.VesselDTO;
import org.springframework.stereotype.Repository;

@Repository
public class VesselDAO extends AbstractDAO<VesselDTO> {

    @Override
    protected Class getTargetClass() {
        return VesselDTO.class;
    }
}
