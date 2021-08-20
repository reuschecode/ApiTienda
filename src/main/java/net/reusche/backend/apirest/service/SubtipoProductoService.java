package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.entity.MarcaProducto;
import net.reusche.backend.apirest.entity.SubtipoProducto;
import net.reusche.backend.apirest.repository.SubtipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubtipoProductoService {

    @Autowired
    SubtipoProductoRepository subtipoProductoRepository;

    /*
    public List<SubtipoProducto> getSubtiposByIdEmpresa(int idEmpresa){
        return subtipoProductoRepository.findAllByEmpresa_IdEmpresa(idEmpresa);
    }

     */

    public Optional<SubtipoProducto> getSubtiposByIdTipo(int idTipo){
        return subtipoProductoRepository.findAllByTipoProducto_IdTipoProducto(idTipo);
    }

    public Optional<SubtipoProducto> getSubtipoByIdSubtipo(Long idSubtipo){
        return subtipoProductoRepository.findById(idSubtipo);
    }

    public void createSubtipo(SubtipoProducto subtipoProducto){
        subtipoProductoRepository.save(subtipoProducto);
    }

    public void updateSubtipo(SubtipoProducto subtipoProducto){
        subtipoProductoRepository.save(subtipoProducto);
    }

    public boolean existByNombre(String nombre){
        return subtipoProductoRepository.existsByNombre(nombre);
    }

}
