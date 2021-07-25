package net.reusche.backend.apirest.repository;

import net.reusche.backend.apirest.entity.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {

    Optional<Tienda> findAllByEmpresa_IdEmpresa(int idEmpresa);

    Tienda findByIdTiendaAndActivoIsTrue(Long idTienda);

}
