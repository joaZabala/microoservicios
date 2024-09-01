package ar.edu.utn.frc.tup.lciii.motoservice.services;


import ar.edu.utn.frc.tup.lciii.motoservice.models.Moto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MotoService {
    Moto getMotoById(Long id);

    List<Moto>getAllMotos();
    List<Moto>getAllMotosByIdUser(Long id);

    Moto createMoto(String marca , String modelo , Long idDuenio);

    Moto getMotoByUserId(Long id);

    Moto updateDummy(Moto dummy);

    void deleteDummy(Moto dummy);
}
