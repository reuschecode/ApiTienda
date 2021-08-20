package net.reusche.backend.apirest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubtipoProductoDto {

    @NotBlank
    private String nombre;

    @NotNull
    private int idTipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipoProducto) {
        this.idTipo = idTipoProducto;
    }
}
