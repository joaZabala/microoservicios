package ar.edu.utn.frc.tup.lciii.motoservice.services.impl;


import ar.edu.utn.frc.tup.lciii.motoservice.entities.MotoEntity;
import ar.edu.utn.frc.tup.lciii.motoservice.models.Moto;
import ar.edu.utn.frc.tup.lciii.motoservice.repositories.MotoRepository;
import ar.edu.utn.frc.tup.lciii.motoservice.services.MotoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoServiceImpl implements MotoService {

    @Autowired
    MotoRepository motoRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Moto getMotoById(Long id) {
        return null;
    }

    @Override
    public List<Moto> getAllMotos() {
        List<MotoEntity> autosEntity = motoRepository.findAll();

        return autosEntity.stream()
                .map(autoEntity -> new Moto(autoEntity.getId() , autoEntity.getMarca()
                        ,autoEntity.getModelo(),autoEntity.getFechaCreacion() , autoEntity.getUsuarioId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Moto> getAllMotosByIdUser(Long id) {
        List<MotoEntity> autosXDuenio = motoRepository.findByUsuarioId(id);

        List<Moto> autos = new ArrayList<>();

        for (MotoEntity a : autosXDuenio){
            autos.add(modelMapper.map(a , Moto.class));
        }

        return autos;
    }

    @Override
    public Moto createMoto(String marca, String modelo, Long idDuenio) {

        //todo verificar el que duenio exista antes de asiganrle el auto
        MotoEntity motoEntity = new MotoEntity();
        motoEntity.setFechaCreacion(LocalDate.now());
        motoEntity.setMarca(marca);
        motoEntity.setModelo(modelo);
        motoEntity.setUsuarioId(idDuenio);

       return modelMapper.map( motoRepository.save(motoEntity) , Moto.class);

    }

    @Override
    public Moto getMotoByUserId(Long id) {
        return null;
    }

    @Override
    public Moto updateDummy(Moto dummy) {
        return null;
    }

    @Override
    public void deleteDummy(Moto dummy) {

    }
}
