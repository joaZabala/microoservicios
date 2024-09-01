package ar.edu.utn.frc.tup.lciii.autosservice.repositories;

import ar.edu.utn.frc.tup.lciii.autosservice.entities.AutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<AutoEntity, Long> {

    List<AutoEntity>findByUsuarioId(Long idUser);
}
