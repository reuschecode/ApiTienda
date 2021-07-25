package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.entity.Empresa;
import net.reusche.backend.apirest.entity.TipoProducto;
import net.reusche.backend.apirest.repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TipoProductoService {

    @Autowired
    TipoProductoRepository tipoProductoRepository;

    public Optional<TipoProducto> getTiposByIdEmpresa(int idEmpresa){
        return tipoProductoRepository.findAllByEmpresa_IdEmpresa(idEmpresa);
    }

    public void createTipoProducto(TipoProducto tipoProducto){
        tipoProductoRepository.save(tipoProducto);
    }

    public void updateTipoProducto(TipoProducto tipoProducto){
        tipoProductoRepository.save(tipoProducto);
    }
}
