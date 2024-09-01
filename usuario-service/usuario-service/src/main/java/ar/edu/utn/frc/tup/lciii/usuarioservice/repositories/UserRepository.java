package ar.edu.utn.frc.tup.lciii.usuarioservice.repositories;

import ar.edu.utn.frc.tup.lciii.usuarioservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
