package net.reusche.backend.apirest.dto;

import net.reusche.backend.apirest.entity.Empresa;
import net.reusche.backend.apirest.entity.MarcaProducto;
import net.reusche.backend.apirest.entity.SubtipoProducto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductoDto {

    private Long idProducto;

    @NotBlank
    private String nombre;

    @NotNull
    @Min(0)
    private Double precio;

    private String urlImagen;

    private boolean activo = true;

    @NotNull
    private int empresa;

    private Long subtipoProducto;

    private Long marcaProducto;

    public String getNombre() {
        return nombre;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public Long getSubtipoProducto() {
        return subtipoProducto;
    }

    public void setSubtipoProducto(Long subtipoProducto) {
        this.subtipoProducto = subtipoProducto;
    }

    public Long getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(Long marcaProducto) {
        this.marcaProducto = marcaProducto;
    }
}
