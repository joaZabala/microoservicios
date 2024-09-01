package ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AltaAutoDto {

    private String marca;

    private String modelo;

}