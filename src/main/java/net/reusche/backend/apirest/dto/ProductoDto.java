package net.reusche.backend.apirest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductoDto {

    @NotBlank
    private String nombre;

    @NotNull
    @Min(0)
    private Double precio;

    private String urlImagen;

    private boolean activo = true;

    @NotNull
    private int idEmpresa;

    private Long idSubtipoProducto;

    private Long idMarcaProducto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getIdSubtipoProducto() {
        return idSubtipoProducto;
    }

    public void setIdSubtipoProducto(Long idSubtipoProducto) {
        this.idSubtipoProducto = idSubtipoProducto;
    }

    public Long getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(Long idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }
}
