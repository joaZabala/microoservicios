package ar.edu.utn.frc.tup.lciii.usuarioservice.services.impl;


import ar.edu.utn.frc.tup.lciii.usuarioservice.FeignClients.AutoFeignClient;
import ar.edu.utn.frc.tup.lciii.usuarioservice.FeignClients.MotoFeignClient;
import ar.edu.utn.frc.tup.lciii.usuarioservice.entities.UserEntity;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.AltaAutoDto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.AltaMotoDto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.Auto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.Moto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.models.User;
import ar.edu.utn.frc.tup.lciii.usuarioservice.repositories.UserRepository;
import ar.edu.utn.frc.tup.lciii.usuarioservice.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AutoFeignClient autoFeignClient;

    @Autowired
    MotoFeignClient motoFeignClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Auto> getAutosByIdUser(Long id){
        User existeUser = getUser(id);
        if(existeUser == null){
            throw new EntityNotFoundException("No existe el usuario ingresado por id");
        }
        List<Auto> autos =
                restTemplate.getForObject("http://localhost:8081/autos/usuario/"+ id ,List.class);
        return autos;
    }
    @Override
    public List<Moto> getMotosByIdUser(Long id){
        User existeUser = getUser(id);
        if(existeUser == null){
            throw new EntityNotFoundException("No existe el usuario ingresado por id");
        }
        List<Moto> motos =
                restTemplate.getForObject("http://localhost:8082/motos/usuario/"+ id ,List.class);
        return motos;
    }

    @Override
    public Auto saveNewCar(Long idUser, AltaAutoDto altaAutoDto) {
        Auto auto = new Auto();
        auto.setUsuarioId(idUser);
        auto.setModelo(altaAutoDto.getModelo());
        auto.setMarca(altaAutoDto.getMarca());
        return autoFeignClient.save(auto);
    }

    @Override
    public Moto saveNewMoto(Long idUser, AltaMotoDto altaMotoDto) {
        Moto moto = new Moto();
        moto.setMarca(altaMotoDto.getMarca());
        moto.setModelo(altaMotoDto.getModelo());
        moto.setUsuarioId(idUser);

        return motoFeignClient.createMoto(moto);
    }

    @Override
    public List<Moto> motosByUserId(Long idUser) {

        return motoFeignClient.getMotos(idUser);
    }

    @Override
    public Map<String, Object> vehiculosByIdUser(Long idUser) {

        Map<String , Object> respuesta = new HashMap<>();

        if(getUser(idUser) == null){
            respuesta.put("Message" , "El user no existe");
            return respuesta;
        }

        List<Auto> autos = autoFeignClient.getAutos(idUser);
        if(autos.isEmpty()){
            respuesta.put("Message" , "El user no tiene autos");
        }else{
            respuesta.put("Autos" , autos);
        }

        List<Moto> motos = motosByUserId(idUser);
        if(motos.isEmpty()){

            respuesta.put("Messagge" , "El user no tine motos");
        }else{
            respuesta.put("Motos" , motos);
        }

        return respuesta;
    }

    @Override
    public User getUser(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"el user no existe");
        }

        return modelMapper.map(userEntityOptional.get() , User.class);
    }

    @Override
    public List<User> getAll() {
        List<UserEntity> list =  userRepository.findAll();

        return  list.stream()
                .map( userEntity ->
                        new User(userEntity.getId(), userEntity.getName() , userEntity.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(String name , String lastName) {

        if(!name.isEmpty() && !lastName.isEmpty()){
            UserEntity userEntity = new UserEntity();
            userEntity.setName(name);
            userEntity.setLastName(lastName);
            return  modelMapper.map(userRepository.save(userEntity), User.class);
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Ingresar nombre y apellido");
    }

    @Override
    public User updateDummy(User dummy) {
        return null;
    }

    @Override
    public void deleteDummy(User dummy) {

    }
}
