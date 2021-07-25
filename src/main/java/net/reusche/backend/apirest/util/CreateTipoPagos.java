/*
package net.reusche.backend.apirest.util;

import net.reusche.backend.apirest.entity.TipoPago;
import net.reusche.backend.apirest.enums.TipoPagoNombre;
import net.reusche.backend.apirest.service.TipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateTipoPagos implements CommandLineRunner {

    @Autowired
    TipoPagoService tipoPagoService;

    @Override
    public void run(String... args) throws Exception {
        for(TipoPagoNombre tipoPagoNombre : TipoPagoNombre.values()){
            TipoPago tipoPago = new TipoPago(tipoPagoNombre);
            tipoPagoService.save(tipoPago);
        }
    }
}
*/