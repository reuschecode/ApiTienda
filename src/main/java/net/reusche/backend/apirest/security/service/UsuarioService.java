package net.reusche.backend.apirest.security.service;

import net.reusche.backend.apirest.security.entity.Usuario;
import net.reusche.backend.apirest.security.entity.interfaces.UsuarioInfo;
import net.reusche.backend.apirest.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioInfo getByEmailUsuarioInfo(String email){
        return usuarioRepository.findByEmailAndActivoIsTrue(email);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
