package net.reusche.backend.apirest.entity;

import javax.persistence.*;

@Entity
public class ProductosTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductosTienda;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_tienda", nullable = false)
    private Tienda tienda;

    @Column(nullable = false)
    private Long cantidad;

    public Long getIdProductosTienda() {
        return idProductosTienda;
    }

    public void setIdProductosTienda(Long idProductosTienda) {
        this.idProductosTienda = idProductosTienda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
