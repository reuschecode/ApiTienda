package net.reusche.backend.apirest.security.repository;

import net.reusche.backend.apirest.security.entity.Usuario;
import net.reusche.backend.apirest.security.entity.interfaces.UsuarioInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    UsuarioInfo findByEmailAndActivoIsTrue(String email);
    boolean existsByEmail(String email);
}
