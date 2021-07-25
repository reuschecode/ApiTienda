package net.reusche.backend.apirest.repository;

import net.reusche.backend.apirest.entity.ProductosTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductosTiendaRepository extends JpaRepository<ProductosTienda, Long> {

    Optional<ProductosTienda> findAllByTienda_IdTienda(Long idTienda);

    boolean existsByTienda_IdTiendaAndProducto_IdProducto(Long idTienda, Long idProducto);
}
