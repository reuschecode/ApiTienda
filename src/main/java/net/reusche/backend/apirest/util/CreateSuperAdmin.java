/*
package net.reusche.backend.apirest.util;

import net.reusche.backend.apirest.entity.Empresa;
import net.reusche.backend.apirest.entity.Tienda;
import net.reusche.backend.apirest.security.entity.Rol;
import net.reusche.backend.apirest.security.entity.Usuario;
import net.reusche.backend.apirest.security.enums.RolNombre;
import net.reusche.backend.apirest.security.service.RolService;
import net.reusche.backend.apirest.security.service.UsuarioService;
import net.reusche.backend.apirest.service.EmpresaService;
import net.reusche.backend.apirest.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateSuperAdmin implements CommandLineRunner {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    TiendaService tiendaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Empresa empresa = new Empresa("ReuscheCode", null, "123456789");
        empresaService.createEmpresa(empresa);
        Tienda tienda = new Tienda("ReuscheCode", "In the Cloud B)", "Cloud", "Cloud", "Cloud", empresa, null);
        tiendaService.createTiendaByEmpresaId(tienda);
        Usuario usuario = new Usuario("Eduardo Julio",
                "reusche488@gmail.com",
                passwordEncoder.encode("reusche_1"),
                "70868712",
                "Reusche Sáenz",
                tienda
        );
        Set<Rol> roles = new HashSet<>();
        for(RolNombre rolNombre : RolNombre.values()){
            Rol rol = rolService.getByNombreRol(rolNombre).get();
            roles.add(rol);
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
    }
}
*/