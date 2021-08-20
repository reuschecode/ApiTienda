package net.reusche.backend.apirest.dto;

public class ProductoActivo {
    private Long idProducto;

    private boolean activo;

    public ProductoActivo(Long idProducto, boolean activo) {
        this.idProducto = idProducto;
        this.activo = activo;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
