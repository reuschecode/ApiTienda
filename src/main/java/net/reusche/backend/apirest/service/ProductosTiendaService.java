package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.entity.Producto;
import net.reusche.backend.apirest.entity.ProductosTienda;
import net.reusche.backend.apirest.repository.ProductosTiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductosTiendaService {

    @Autowired
    ProductosTiendaRepository productosTiendaRepository;

    public Optional<ProductosTienda> getProductosByTiendaProducto(Long idTienda){
        return productosTiendaRepository.findAllByTienda_IdTienda(idTienda);
    }

    public void addProductoTiendaById(ProductosTienda productosTienda, Long cantidadIngresada){
        if(productosTiendaRepository.existsByTienda_IdTiendaAndProducto_IdProducto(productosTienda.getTienda().getIdTienda(), productosTienda.getProducto().getIdProducto())){
            Long cantidadProductos = productosTienda.getCantidad();
            productosTienda.setCantidad(cantidadProductos + cantidadIngresada);
        }
        productosTiendaRepository.save(productosTienda);
    }
}
