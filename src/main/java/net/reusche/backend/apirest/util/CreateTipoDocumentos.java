/*
package net.reusche.backend.apirest.util;

import net.reusche.backend.apirest.entity.TipoDocumento;
import net.reusche.backend.apirest.enums.TipoDocumentoNombre;
import net.reusche.backend.apirest.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateTipoDocumentos implements CommandLineRunner {

    @Autowired
    TipoDocumentoService tipoDocumentoService;

    @Override
    public void run(String... args) throws Exception {
        for(TipoDocumentoNombre tipoDocumentoNombre : TipoDocumentoNombre.values()){
            TipoDocumento tipoDocumento = new TipoDocumento(tipoDocumentoNombre);
            tipoDocumentoService.save(tipoDocumento);
        }
    }
}
*/