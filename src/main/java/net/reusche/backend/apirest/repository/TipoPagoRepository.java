package net.reusche.backend.apirest.repository;

import net.reusche.backend.apirest.entity.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {
}
