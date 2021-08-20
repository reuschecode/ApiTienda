package net.reusche.backend.apirest.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpresa;

    @NotNull
    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(length = 100)
    private String urlImagen;

    @Column(length = 45, unique = true)
    private String ruc;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1", nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy = "empresa")
    private Set<Tienda> tiendas;

    @OneToMany(mappedBy = "empresa")
    private Set<MarcaProducto> marcaProductos;

    @OneToMany(mappedBy = "empresa")
    private Set<Producto> productos;

    @OneToMany(mappedBy = "empresa")
    private Set<TipoProducto> tipoProductos;

    public Empresa(@NotNull String nombre, String urlImagen, String ruc) {
        this.nombre = nombre;
        this.urlImagen = urlImagen;
        this.ruc = ruc;
    }

    public Empresa() {

    }

    public Empresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombreEmpresa(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }



    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
