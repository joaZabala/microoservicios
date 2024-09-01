package ar.edu.utn.frc.tup.lciii.motoservice.repositories;

import ar.edu.utn.frc.tup.lciii.motoservice.entities.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<MotoEntity, Long> {

    List<MotoEntity>findByUsuarioId(Long idUser);
}
