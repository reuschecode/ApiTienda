package net.reusche.backend.apirest.entity;

import net.reusche.backend.apirest.security.entity.Usuario;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTienda;

    @Column(length = 45, nullable = false)
    private String nombre;

    @Column(length = 45, nullable = false)
    private String direccionTienda;

    @Column(length = 45, nullable = false)
    private String departamentoTienda;

    @Column(length = 45)
    private String provinciaTienda;

    @Column(length = 45)
    private String distritoTienda;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "tienda")
    private Set<Usuario> usuarios;

    @Column(length = 100)
    private String urlImagen;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1", nullable = false)
    private boolean activo = true;

    public Tienda(String nombre, String direccionTienda, String departamentoTienda, String provinciaTienda, String distritoTienda, Empresa empresa, String urlImagen) {
        this.nombre = nombre;
        this.direccionTienda = direccionTienda;
        this.departamentoTienda = departamentoTienda;
        this.provinciaTienda = provinciaTienda;
        this.distritoTienda = distritoTienda;
        this.empresa = empresa;
        this.urlImagen = urlImagen;
    }

    public Tienda() {

    }

    public Long getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Long idTienda) {
        this.idTienda = idTienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionTienda() {
        return direccionTienda;
    }

    public String getDepartamentoTienda() { return departamentoTienda; }

    public void setDepartamentoTienda(String departamentoTienda) { this.departamentoTienda = departamentoTienda; }

    public void setDireccionTienda(String direccionTienda) {
        this.direccionTienda = direccionTienda;
    }

    public String getProvinciaTienda() {
        return provinciaTienda;
    }

    public void setProvinciaTienda(String provinciaTienda) {
        this.provinciaTienda = provinciaTienda;
    }

    public String getDistritoTienda() {
        return distritoTienda;
    }

    public void setDistritoTienda(String distritoTienda) {
        this.distritoTienda = distritoTienda;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
