package ar.edu.utn.frc.tup.lciii.autosservice.controllers;

import ar.edu.utn.frc.tup.lciii.autosservice.dtos.AutoDto;
import ar.edu.utn.frc.tup.lciii.autosservice.models.Auto;
import ar.edu.utn.frc.tup.lciii.autosservice.services.AutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autos")
public class AutoController {

    @Autowired
   private AutoService autoService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    private ResponseEntity<List<AutoDto>> getAll(){
       List<Auto> autos=  autoService.getAllAutos();

        return
                ResponseEntity.ok( autos.stream()
                        .map(auto -> new AutoDto(auto.getId() , auto.getMarca()
                                , auto.getModelo() , auto.getFechaCreacion() , auto.getUsuarioId()))
                        .collect(Collectors.toList()) );
    }


    @GetMapping("/usuario/{id}")
    private ResponseEntity<List<AutoDto>> getAllByUserId(@PathVariable Long id){
        List<Auto> autos=  autoService.getAllAutosByIdUser(id);

        return
                ResponseEntity.ok( autos.stream()
                        .map(auto -> new AutoDto(auto.getId() , auto.getMarca()
                                , auto.getModelo() , auto.getFechaCreacion() , auto.getUsuarioId()))
                        .collect(Collectors.toList()) );
    }
    @GetMapping("{id}")
    private ResponseEntity<AutoDto> getAutoById(@PathVariable Long id){
       Auto auto = autoService.getAutoById(id);
       return ResponseEntity.ok(modelMapper.map(auto , AutoDto.class));
    }
    @PostMapping("/create")
    private ResponseEntity<AutoDto> postAuto(@RequestBody AutoDto autoDto){
       Auto auto = autoService.createAuto(autoDto.getMarca(),autoDto.getModelo(),autoDto.getUsuarioId());
        return ResponseEntity.ok(modelMapper.map(auto , AutoDto.class));
    }
    @PutMapping("")
    private ResponseEntity<AutoDto> putDummy(AutoDto dummyDto){
        Auto dummy = autoService.updateDummy(null);
        return null;
    }
    @DeleteMapping("")
    private ResponseEntity<AutoDto> deleteDummy(AutoDto dummyDto){
        autoService.deleteDummy(null);
        return null;
    }
}
