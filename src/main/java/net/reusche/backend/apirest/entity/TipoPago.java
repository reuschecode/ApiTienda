package net.reusche.backend.apirest.entity;

import net.reusche.backend.apirest.enums.TipoPagoNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class TipoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDocumento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 25, nullable = false)
    private TipoPagoNombre nombre;

    @OneToMany(mappedBy = "tipoPago")
    private Set<Documento> documentos;

    public TipoPago(@NotNull TipoPagoNombre nombre) {
        this.nombre = nombre;
    }

    public TipoPago() {

    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public TipoPagoNombre getNombre() {
        return nombre;
    }

    public void setNombre(TipoPagoNombre nombre) {
        this.nombre = nombre;
    }
}
