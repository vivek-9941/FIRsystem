package org.fir.firsystem.Service.Implementation;

import org.fir.firsystem.Model.Incidence;
import org.fir.firsystem.Repository.IncidenceRepository;
import org.fir.firsystem.Service.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenceServiceImplementation implements IncidenceService {
    @Autowired
    IncidenceRepository incidenceRepository;

    @Override
    public Incidence save(Incidence incidence) {
        return incidenceRepository.save(incidence);
    }
}
