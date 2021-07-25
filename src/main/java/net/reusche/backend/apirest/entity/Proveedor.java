package net.reusche.backend.apirest.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
public class Proveedor {

    @Id
    @Column(columnDefinition = "CHAR(8)")
    private String  dniProveedor;

    @Column(length = 100, nullable = false)
    private String nombres;

    @Column(length = 12, nullable = false)
    private String telefonoContacto1;

    @Column(length = 12)
    private String telefonoContacto2;

    @Column(length = 45, nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "proveedor")
    private Set<Documento> documentos;

    public String getDniProveedor() {
        return dniProveedor;
    }

    public void setDniProveedor(String dniProveedor) {
        this.dniProveedor = dniProveedor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefonoContacto1() {
        return telefonoContacto1;
    }

    public void setTelefonoContacto1(String telefonoContacto1) {
        this.telefonoContacto1 = telefonoContacto1;
    }

    public String getTelefonoContacto2() {
        return telefonoContacto2;
    }

    public void setTelefonoContacto2(String telefonoContacto2) {
        this.telefonoContacto2 = telefonoContacto2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
