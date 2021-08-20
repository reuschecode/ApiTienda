package net.reusche.backend.apirest.repository;

import net.reusche.backend.apirest.entity.MarcaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaProductoRepository extends JpaRepository<MarcaProducto, Long> {

    List<MarcaProducto> findAllByEmpresa_IdEmpresa(int idEmpresa);

    boolean existsByNombre(String nombre);
}
