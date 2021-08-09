package net.reusche.backend.apirest.security.controller;

import net.reusche.backend.apirest.entity.Tienda;
import net.reusche.backend.apirest.security.dto.JwtDto;
import net.reusche.backend.apirest.security.dto.LoginUsuario;
import net.reusche.backend.apirest.security.dto.NuevoUsuario;
import net.reusche.backend.apirest.security.entity.Rol;
import net.reusche.backend.apirest.security.entity.Usuario;
import net.reusche.backend.apirest.security.entity.interfaces.UsuarioInfo;
import net.reusche.backend.apirest.security.enums.RolNombre;
import net.reusche.backend.apirest.security.jwt.JwtProvider;
import net.reusche.backend.apirest.security.service.RolService;
import net.reusche.backend.apirest.security.service.UsuarioService;
import net.reusche.backend.apirest.service.TiendaService;
import net.reusche.backend.apirest.util.JsonMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    TiendaService tiendaService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new JsonMessageResponse("Faltan datos o email/contraseña son inválidos.", "ERROR"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new JsonMessageResponse("Ya existe un usuario con ese email.", "ERROR"), HttpStatus.BAD_REQUEST);
        Tienda tienda = tiendaService.getTiendaByIdTienda(nuevoUsuario.getIdTienda());
        if(tienda == null)
            return new ResponseEntity(new JsonMessageResponse("No se encontró la tienda especificada.", "ERROR"), HttpStatus.BAD_REQUEST);
        Usuario usuario = new Usuario(nuevoUsuario.getNombres(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()),
                nuevoUsuario.getDni(),
                nuevoUsuario.getApellidos(),
                tienda
        );

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByNombreRol(RolNombre.VENDEDOR_TIENDA).get());
        if(nuevoUsuario.getRoles().contains("ADMINISTRADOR DE TIENDA"))
            roles.add(rolService.getByNombreRol(RolNombre.ADMIN_TIENDA).get());
        if(nuevoUsuario.getRoles().contains("ADMINISTRADOR DE EMPRESA"))
            roles.add(rolService.getByNombreRol(RolNombre.ADMIN_EMPRESA).get());
        if(nuevoUsuario.getRoles().contains("SUPERVISOR DE TIENDA"))
            roles.add(rolService.getByNombreRol(RolNombre.SUPERVISOR).get());
        if(nuevoUsuario.getRoles().contains("SUPERADMIN"))
            roles.add(rolService.getByNombreRol(RolNombre.SUPER_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new JsonMessageResponse("Usuario guardado con éxito.", "OK"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> Login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new JsonMessageResponse("Email y/o contraseña inválidos.", "ERROR"), HttpStatus.BAD_REQUEST);
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            String jwt = jwtProvider.generateToken(auth);
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            UsuarioInfo usuario = usuarioService.getByEmailUsuarioInfo(userDetails.getUsername());
            if(usuario == null)
                return new ResponseEntity(new JsonMessageResponse("Usuario deshabilitado.", "ERROR"), HttpStatus.BAD_REQUEST);
            JwtDto jwtDto = new JwtDto(jwt, usuario, userDetails.getAuthorities());
            return new ResponseEntity(jwtDto, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(new JsonMessageResponse("No existe un usuario con ese email o contraseña.", "NOT_FOUND"), HttpStatus.BAD_REQUEST);
        }
    }

}
