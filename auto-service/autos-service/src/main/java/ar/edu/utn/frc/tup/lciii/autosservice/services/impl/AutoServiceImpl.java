package ar.edu.utn.frc.tup.lciii.autosservice.services.impl;

import ar.edu.utn.frc.tup.lciii.autosservice.entities.AutoEntity;
import ar.edu.utn.frc.tup.lciii.autosservice.models.Auto;
import ar.edu.utn.frc.tup.lciii.autosservice.repositories.AutoRepository;
import ar.edu.utn.frc.tup.lciii.autosservice.services.AutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutoServiceImpl implements AutoService {

    @Autowired
    AutoRepository autoRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Auto getAutoById(Long id) {
        return null;
    }

    @Override
    public List<Auto> getAllAutos() {
        List<AutoEntity> autosEntity = autoRepository.findAll();

        return autosEntity.stream()
                .map(autoEntity -> new Auto(autoEntity.getId() , autoEntity.getMarca()
                        ,autoEntity.getModelo(),autoEntity.getFechaCreacion() , autoEntity.getUsuarioId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Auto> getAllAutosByIdUser(Long id) {
        List<AutoEntity> autosXDuenio = autoRepository.findByUsuarioId(id);

        List<Auto> autos = new ArrayList<>();

        for (AutoEntity a : autosXDuenio){
            autos.add(modelMapper.map(a , Auto.class));
        }

        return autos;
    }

    @Override
    public Auto createAuto(String marca, String modelo, Long idDuenio) {

        //todo verificar el que duenio exista antes de asiganrle el auto
        AutoEntity autoEntity = new AutoEntity();
        autoEntity.setFechaCreacion(LocalDate.now());
        autoEntity.setMarca(marca);
        autoEntity.setModelo(modelo);
        autoEntity.setUsuarioId(idDuenio);

       return modelMapper.map( autoRepository.save(autoEntity) , Auto.class);

    }

    @Override
    public Auto getAutoByUserId(Long id) {
        return null;
    }

    @Override
    public Auto updateDummy(Auto dummy) {
        return null;
    }

    @Override
    public void deleteDummy(Auto dummy) {

    }
}
