package ar.edu.utn.frc.tup.lciii.autosservice.services;

import ar.edu.utn.frc.tup.lciii.autosservice.models.Auto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AutoService {
    Auto getAutoById(Long id);

    List<Auto>getAllAutos();
    List<Auto>getAllAutosByIdUser(Long id);

    Auto createAuto(String marca , String modelo , Long idDuenio);

    Auto getAutoByUserId(Long id);

    Auto updateDummy(Auto dummy);

    void deleteDummy(Auto dummy);
}
