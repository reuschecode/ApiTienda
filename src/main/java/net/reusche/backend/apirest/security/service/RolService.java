package net.reusche.backend.apirest.security.service;

import net.reusche.backend.apirest.security.entity.Rol;
import net.reusche.backend.apirest.security.enums.RolNombre;
import net.reusche.backend.apirest.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByNombreRol(RolNombre nombreRol){
        return rolRepository.findByNombreRol(nombreRol);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
