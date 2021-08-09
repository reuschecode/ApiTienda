package net.reusche.backend.apirest.repository;

import net.reusche.backend.apirest.entity.TipoProducto;
import net.reusche.backend.apirest.entity.interfaces.TiposWithSubtipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {

    List<TiposWithSubtipos> findAllByEmpresa_IdEmpresa(int idEmpresa);
}
