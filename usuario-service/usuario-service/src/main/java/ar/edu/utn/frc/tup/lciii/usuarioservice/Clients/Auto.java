package ar.edu.utn.frc.tup.lciii.usuarioservice.Clients;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auto {
    private Long id;

    private String marca;

    private String modelo;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyy")
    private LocalDate fechaCreacion;

    private Long usuarioId;
}
