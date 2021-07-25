package net.reusche.backend.apirest.security.repository;

import net.reusche.backend.apirest.security.entity.Rol;
import net.reusche.backend.apirest.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombreRol(RolNombre nombreRol);
}
