package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.entity.TipoDocumento;
import net.reusche.backend.apirest.entity.TipoPago;
import net.reusche.backend.apirest.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TipoDocumentoService {

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    public void save(TipoDocumento tipoDocumento){
        tipoDocumentoRepository.save(tipoDocumento);
    }
}
