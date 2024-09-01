package ar.edu.utn.frc.tup.lciii.usuarioservice.FeignClients;

import ar.edu.utn.frc.tup.lciii.usuarioservice.modelosCliente.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "moto-service" , url = "http://localhost:8082/motos")
public interface MotoFeignClient {
    @PostMapping("/save")
    Moto createMoto(@RequestBody Moto moto);

    @GetMapping("/usuario/{id}")
    List<Moto> getMotos(@PathVariable Long id);
}
