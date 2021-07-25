package net.reusche.backend.apirest.entity;

import net.reusche.backend.apirest.enums.TipoDocumentoNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoDocumento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 25, nullable = false)
    private TipoDocumentoNombre nombre;

    @OneToMany(mappedBy = "tipoDocumento")
    private Set<Documento> documentos;

    public TipoDocumento(@NotNull TipoDocumentoNombre nombre) {
        this.nombre = nombre;
    }

    public TipoDocumento() {

    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public TipoDocumentoNombre getNombre() {
        return nombre;
    }

    public void setNombre(TipoDocumentoNombre nombre) {
        this.nombre = nombre;
    }
}
