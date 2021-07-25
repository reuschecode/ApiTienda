package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.entity.TipoPago;
import net.reusche.backend.apirest.repository.TipoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TipoPagoService {

    @Autowired
    TipoPagoRepository tipoPagoRepository;

    public void save(TipoPago tipoPago){
        tipoPagoRepository.save(tipoPago);
    }
}
