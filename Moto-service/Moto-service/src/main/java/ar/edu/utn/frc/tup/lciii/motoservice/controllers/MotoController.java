package ar.edu.utn.frc.tup.lciii.motoservice.controllers;

import ar.edu.utn.frc.tup.lciii.motoservice.dtos.MotoDto;
import ar.edu.utn.frc.tup.lciii.motoservice.models.Moto;
import ar.edu.utn.frc.tup.lciii.motoservice.services.MotoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    private ResponseEntity<List<MotoDto>> getAll() {
        List<Moto> motos = motoService.getAllMotos();

        return
                ResponseEntity.ok(motos.stream()
                        .map(moto -> new MotoDto(moto.getId(), moto.getMarca()
                                , moto.getModelo(), moto.getFechaCreacion(), moto.getUsuarioId()))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/usuario/{id}")
    private ResponseEntity<List<MotoDto>> getAllByIdUser(@PathVariable Long id) {
        List<Moto> motos = motoService.getAllMotosByIdUser(id);

        return
                ResponseEntity.ok(motos.stream()
                        .map(moto -> new MotoDto(moto.getId(), moto.getMarca()
                                , moto.getModelo(), moto.getFechaCreacion(), moto.getUsuarioId()))
                        .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    private ResponseEntity<MotoDto> getMotoById(@PathVariable Long id) {
        Moto auto = motoService.getMotoById(id);
        return ResponseEntity.ok(modelMapper.map(auto, MotoDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<MotoDto> postMoto(@RequestBody MotoDto motoDto) {
        Moto auto = motoService.createMoto(motoDto.getMarca(), motoDto.getModelo(), motoDto.getUsuarioId());
        return ResponseEntity.ok(modelMapper.map(auto, MotoDto.class));
    }

    @PutMapping("")
    private ResponseEntity<MotoDto> putDummy(MotoDto dummyDto) {
        Moto dummy = motoService.updateDummy(null);
        return null;
    }

    @DeleteMapping("")
    private ResponseEntity<MotoDto> deleteDummy(MotoDto dummyDto) {
        motoService.deleteDummy(null);
        return null;
    }
}
