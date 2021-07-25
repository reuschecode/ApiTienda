package net.reusche.backend.apirest.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cliente {

    @Id
    @Column(columnDefinition = "CHAR(8)")
    private String dniCliente;

    @Column(length = 100, nullable = false)
    private String nombres;

    @OneToMany(mappedBy = "cliente")
    private Set<Documento> documentos;

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombre(String nombres) {
        this.nombres = nombres;
    }
}
