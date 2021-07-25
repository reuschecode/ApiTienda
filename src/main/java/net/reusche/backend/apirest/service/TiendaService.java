package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.entity.Tienda;
import net.reusche.backend.apirest.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TiendaService {

    @Autowired
    TiendaRepository tiendaRepository;

    public Optional<Tienda> getTiendasByIdEmpresa(int idEmpresa){
        return tiendaRepository.findAllByEmpresa_IdEmpresa(idEmpresa);
    }

    public void createTiendaByEmpresaId(Tienda tienda){
        tiendaRepository.save(tienda);
    }

    public Tienda getTiendaByIdTienda(Long idTienda){
        return tiendaRepository.findByIdTiendaAndActivoIsTrue(idTienda);
    }

    public void updateTienda(Tienda tienda){
        tiendaRepository.save(tienda);
    }

    public void changeTiendaActivo(Tienda tienda){
        tienda.setActivo(!tienda.isActivo());
        tiendaRepository.save(tienda);
    }

}
