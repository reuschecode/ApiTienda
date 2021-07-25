package net.reusche.backend.apirest.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SubtipoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubtipoProducto;

    @Column(length = 45, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto", nullable = false)
    private TipoProducto tipoProducto;

    @OneToMany(mappedBy = "subtipoProducto")
    private Set<Producto> productos;

    public Long getIdSubtipoProducto() {
        return idSubtipoProducto;
    }

    public void setIdMarcaProducto(Long idSubtipoProducto) {
        this.idSubtipoProducto = idSubtipoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}
