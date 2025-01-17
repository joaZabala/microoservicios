package ar.edu.utn.frc.tup.lciii.usuarioservice.services;


import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.AltaAutoDto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.AltaMotoDto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.Auto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.Moto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    User getUser(Long id);

    List<User>getAll();

    User createUser(String name , String lastName);

    List<Auto>getAutosByIdUser(Long idUser);
    List<Moto>getMotosByIdUser(Long idUser);

    Auto saveNewCar(Long idUser , AltaAutoDto altaAutoDto);

    Moto saveNewMoto(Long idUser , AltaMotoDto altaMotoDto);

    List<Moto> motosByUserId(Long idUser);

    Map<String , Object> vehiculosByIdUser(Long idUser);
    User updateDummy(User dummy);

    void deleteDummy(User dummy);
}
