package net.reusche.backend.apirest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MarcaProductoDto {

    @NotBlank
    private String nombre;

    @NotNull
    private int idEmpresa;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
