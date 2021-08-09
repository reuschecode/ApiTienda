package net.reusche.backend.apirest.entity.interfaces;

import java.util.Set;

public interface TiposWithSubtipos {

    int getIdTipoProducto();
    String getNombre();
    Set<SubtipoNameAndId> getSubtipoProductos();
}
