package net.reusche.backend.apirest.repository;

import net.reusche.backend.apirest.entity.SubtipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubtipoProductoRepository extends JpaRepository<SubtipoProducto, Long> {

    Optional<SubtipoProducto> findAllByTipoProducto_IdTipoProducto(int idTipoProducto);

}
