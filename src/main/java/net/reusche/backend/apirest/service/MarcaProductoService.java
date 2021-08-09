package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.entity.MarcaProducto;
import net.reusche.backend.apirest.entity.Producto;
import net.reusche.backend.apirest.repository.MarcaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarcaProductoService {

    @Autowired
    MarcaProductoRepository marcaProductoRepository;

    public List<MarcaProducto> getMarcasByEmpresaId(int idEmpresa){
        return marcaProductoRepository.findAllByEmpresa_IdEmpresa(idEmpresa);
    }
    public Optional<MarcaProducto> getMarcaByIdMarca(Long idMarca){
        return marcaProductoRepository.findById(idMarca);
    }

    public void createMarca(MarcaProducto marcaProducto){
        marcaProductoRepository.save(marcaProducto);
    }

    public void updateMarca(MarcaProducto marcaProducto){
        marcaProductoRepository.save(marcaProducto);
    }
}
