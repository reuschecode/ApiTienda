package net.reusche.backend.apirest.security.entity;

import net.reusche.backend.apirest.security.enums.RolNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private RolNombre nombreRol;

    public Rol() {
    }

    public Rol(@NotNull RolNombre nombreRol) {
        this.nombreRol = nombreRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int id) {
        this.idRol = id;
    }

    public RolNombre getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(RolNombre nombreRol) {
        this.nombreRol = nombreRol;
    }
}