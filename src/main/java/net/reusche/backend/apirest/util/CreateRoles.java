/*
package net.reusche.backend.apirest.util;

import net.reusche.backend.apirest.security.entity.Rol;
import net.reusche.backend.apirest.security.enums.RolNombre;
import net.reusche.backend.apirest.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        for(RolNombre rol : RolNombre.values()){
            Rol nuevoRol = new Rol(rol);
            rolService.save(nuevoRol);
        }
    }
}
*/