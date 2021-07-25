package net.reusche.backend.apirest.security.entity.interfaces;

import net.reusche.backend.apirest.entity.Tienda;

public interface UsuarioInfo {

    int getIdUsuario();
    String getNombres();
    String getEmail();
    Tienda getTienda();
    boolean isActivo();
}
