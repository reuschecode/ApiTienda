package net.reusche.backend.apirest.security.entity;

import net.reusche.backend.apirest.entity.Tienda;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @NotNull
    @Column(length = 45, nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(columnDefinition = "CHAR(8)", nullable = false)
    private String dni;

    @ManyToOne
    @JoinColumn(name = "idTienda", referencedColumnName = "idTienda")
    private Tienda tienda;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) DEFAULT 1", nullable = false)
    private boolean activo = true;

    @Column(length = 50)
    private String nombres;

    @Column(length = 50)
    private String apellidos;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String nombres, @NotNull String email, @NotNull String password, @NotNull String dni, String apellidos, Tienda tienda) {
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.tienda = tienda;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombre) {
        this.nombres = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Tienda getTienda() { return tienda; }

    public void setTienda(Tienda tienda) { this.tienda = tienda; }
}