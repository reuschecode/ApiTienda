package net.reusche.backend.apirest.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TipoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoProducto;

    @Column(length = 45,nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "tipoProducto")
    private Set<SubtipoProducto> subtipoProductos;

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
