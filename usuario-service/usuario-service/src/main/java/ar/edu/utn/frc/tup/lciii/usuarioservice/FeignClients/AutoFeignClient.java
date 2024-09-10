package ar.edu.utn.frc.tup.lciii.usuarioservice.FeignClients;

import ar.edu.utn.frc.tup.lciii.usuarioservice.Clients.Auto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "auto-service", url = "http://localhost:8081/autos")
public interface AutoFeignClient {

    @PostMapping("/create")
    Auto save(@RequestBody Auto auto);

    @GetMapping("/usuario/{id}")
    List<Auto> getAutos(@PathVariable Long id);
}
