package ar.edu.utn.frc.tup.lciii.usuarioservice.controllers;

import ar.edu.utn.frc.tup.lciii.usuarioservice.dtos.UserDto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.AltaAutoDto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.AltaMotoDto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.Auto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.Moto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.models.User;
import ar.edu.utn.frc.tup.lciii.usuarioservice.services.UserService;
import ch.qos.logback.core.model.Model;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
   private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    private ResponseEntity<List<UserDto>> getAllUsers(){
        List<User>users = userService.getAll();

        return ResponseEntity.ok( users.stream().map(user -> new UserDto(user.getName() , user.getLastName()))
                .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    private ResponseEntity<UserDto> getUserById(@PathVariable Long id){

        return ResponseEntity.ok(modelMapper.map(userService.getUser(id) , UserDto.class));
    }
    @PostMapping("/create")
    private ResponseEntity<UserDto> postUser(UserDto userDto){
       User user = userService.createUser(userDto.getName() , userDto.getLastName());
        return ResponseEntity.ok(modelMapper.map(user , UserDto.class));
    }


    @GetMapping("/autos/usuario/{idUsuario}")
    public ResponseEntity<List<Auto>>getAutos(@PathVariable("idUsuario") Long idUsuario){

        return ResponseEntity.ok(userService.getAutosByIdUser(idUsuario));
    }

    @GetMapping("/motos/usuario/{idUsuario}")
    public ResponseEntity<List<Moto>>getMotos(@PathVariable("idUsuario") Long idUsuario){

        return ResponseEntity.ok(userService.getMotosByIdUser(idUsuario));
    }

    @PostMapping("/auto/{idUser}")
    public ResponseEntity<Auto>saveCar(@PathVariable Long idUser , @RequestBody AltaAutoDto altaAutoDto){

        return ResponseEntity.ok(userService.saveNewCar(idUser, altaAutoDto));
    }

    @PostMapping("/moto/{idUser}")
    public ResponseEntity<Moto>saveMoto(@PathVariable Long idUser , @RequestBody AltaMotoDto altaMotoDto){

        return ResponseEntity.ok(userService.saveNewMoto(idUser, altaMotoDto));
    }

    @GetMapping("vehiculos/{id}")
    public ResponseEntity<Map<String , Object>>getVehicules(@PathVariable Long id){
        return ResponseEntity.ok(userService.vehiculosByIdUser(id));
    }

    @PutMapping("")
    private ResponseEntity<UserDto> putDummy(UserDto dummyDto){
     userService.updateDummy(null);
        return null;
    }
    @DeleteMapping("")
    private ResponseEntity<UserDto> deleteDummy(UserDto dummyDto){
        userService.deleteDummy(null);
        return null;
    }
}
