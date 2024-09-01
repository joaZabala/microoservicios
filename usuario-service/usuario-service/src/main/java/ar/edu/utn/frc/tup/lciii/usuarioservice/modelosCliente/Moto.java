package ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moto {

    private Long id;

    private String marca;

    private String modelo;

    private LocalDate fechaCreacion;

    private Long usuarioId;
}
