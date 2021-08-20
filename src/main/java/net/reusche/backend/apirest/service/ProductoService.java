package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.dto.ProductoActivo;
import net.reusche.backend.apirest.entity.Producto;
import net.reusche.backend.apirest.entity.interfaces.ProductoNameAndId;
import net.reusche.backend.apirest.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public void createProducto(Producto producto){
        productoRepository.save(producto);
    }

    public List<Producto> getProductosByEmpresaId(int idEmpresa){
        return productoRepository.findAllByEmpresa_IdEmpresaOrderByIdProductoDesc(idEmpresa);
    }

    public Optional<Producto> getProductoById(Long idProducto){
        return productoRepository.findById(idProducto);
    }

    public List<ProductoNameAndId> getProductosOnlyNameAndId(Integer idEmpresa){
        return productoRepository.findAllByEmpresa_IdEmpresaAndActivoIsTrueOrderByIdProductoDesc(idEmpresa);
    }

    public void changeProductoActivo(ProductoActivo producto){
        producto.setActivo(!producto.isActivo());
        productoRepository.changeActivo(producto.getIdProducto(), producto.isActivo());
    }

    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }

    public boolean existByNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }
}
