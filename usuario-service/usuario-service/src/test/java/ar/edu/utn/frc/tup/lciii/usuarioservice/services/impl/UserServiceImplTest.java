package ar.edu.utn.frc.tup.lciii.usuarioservice.services.impl;

import ar.edu.utn.frc.tup.lciii.usuarioservice.Clients.Auto;
import ar.edu.utn.frc.tup.lciii.usuarioservice.entities.UserEntity;
import ar.edu.utn.frc.tup.lciii.usuarioservice.models.User;
import ar.edu.utn.frc.tup.lciii.usuarioservice.repositories.UserRepository;
import ar.edu.utn.frc.tup.lciii.usuarioservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    RestTemplate restTemplate;

    @SpyBean
    UserServiceImpl userService;
    @Test
    void getAutosByIdUser() {

        List<Auto> autos = new ArrayList<>();
        autos.add(new Auto());
        autos.add(new Auto());

//        when(userService.getUser(1L)).thenReturn(new User());

        when(userRepository.findById(1L)).thenReturn(Optional.of(new UserEntity()));

        when(restTemplate.getForObject("http://localhost:8081/autos/usuario/"+ 1L ,List.class))
                .thenReturn(autos);

        List<Auto> autosResult = userService.getAutosByIdUser(1L);

        assertEquals(2 , autosResult.size());
    }

    @Test
    void ausotsByUserId() {
    }

    @Test
    void getMotosByIdUser() {
    }

    @Test
    void saveNewCar() {
    }

    @Test
    void saveNewMoto() {
    }

    @Test
    void motosByUserId() {
    }

    @Test
    void vehiculosByIdUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getAll() {
    }

    @Test
    void createUser() {
    }
}