package net.reusche.backend.apirest.repository;

import net.reusche.backend.apirest.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {

    Optional<TipoProducto> findAllByEmpresa_IdEmpresa(int idEmpresa);
}
