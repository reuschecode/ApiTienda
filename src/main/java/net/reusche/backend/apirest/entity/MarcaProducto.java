package net.reusche.backend.apirest.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MarcaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarcaProducto;

    @Column(length = 45)
    private String nombre;

    @ManyToOne
    @JoinColumn(name="id_empresa", nullable=false)
    private Empresa empresa;

    @OneToMany(mappedBy = "marcaProducto")
    private Set<Producto> productos;

    public Long getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(Long idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
