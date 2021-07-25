package net.reusche.backend.apirest.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(length = 45, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column(length = 100)
    private String urlImagen;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1", nullable = false)
    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "id_subtipo_producto")
    private SubtipoProducto subtipoProducto;

    @ManyToOne
    @JoinColumn(name = "id_marca_producto")
    private MarcaProducto marcaProducto;

    /*
    @OneToMany(mappedBy = "producto")
    private Set<Detalle> detalles;
    */

    public Producto(String nombre, double precio, String urlImagen, boolean activo, Empresa empresa, SubtipoProducto subtipoProducto, MarcaProducto marcaProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
        this.activo = activo;
        this.empresa = empresa;
        this.subtipoProducto = subtipoProducto;
        this.marcaProducto = marcaProducto;
    }

    public Producto() {

    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public SubtipoProducto getSubtipoProducto() {
        return subtipoProducto;
    }

    public void setSubtipoProducto(SubtipoProducto subtipoProducto) {
        this.subtipoProducto = subtipoProducto;
    }

    public MarcaProducto getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(MarcaProducto marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    //MARCA_PRODUCTO
}
