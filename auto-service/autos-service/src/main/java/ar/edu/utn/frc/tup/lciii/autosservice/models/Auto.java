package ar.edu.utn.frc.tup.lciii.autosservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auto {
    private Long id;

    private String marca;

    private String modelo;

    private LocalDate fechaCreacion;

    private Long usuarioId;
}
