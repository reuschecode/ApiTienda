package net.reusche.backend.apirest.controller;

import net.reusche.backend.apirest.dto.ProductoDto;
import net.reusche.backend.apirest.entity.Empresa;
import net.reusche.backend.apirest.entity.MarcaProducto;
import net.reusche.backend.apirest.entity.Producto;
import net.reusche.backend.apirest.entity.SubtipoProducto;
import net.reusche.backend.apirest.entity.interfaces.ProductoNameAndId;
import net.reusche.backend.apirest.service.EmpresaService;
import net.reusche.backend.apirest.service.MarcaProductoService;
import net.reusche.backend.apirest.service.ProductoService;
import net.reusche.backend.apirest.service.SubtipoProductoService;
import net.reusche.backend.apirest.util.JsonMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("http://localhost:4200")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    SubtipoProductoService subtipoProductoService;

    @Autowired
    MarcaProductoService marcaProductoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ProductoDto productoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de producto mal escritos o faltantes, revisar antes de guardar el producto.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(productoService.existByNombre(productoDto.getNombre())){
            return new ResponseEntity(new JsonMessageResponse("Ya existe un producto con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        Empresa empresa = empresaService.getByIdEmpresa(productoDto.getIdEmpresa()).get();
        SubtipoProducto subtipoProducto = null;
        MarcaProducto marcaProducto = null;
        if(productoDto.getIdSubtipoProducto() != null){
            subtipoProducto = subtipoProductoService.getSubtipoByIdSubtipo(productoDto.getIdSubtipoProducto()).get();
        }
        if(productoDto.getIdSubtipoProducto() != null){
            marcaProducto = marcaProductoService.getMarcaByIdMarca(productoDto.getIdMarcaProducto()).get();
        }
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio(), productoDto.getUrlImagen(), productoDto.isActivo(), empresa, subtipoProducto, marcaProducto);
        productoService.createProducto(producto);

        return new ResponseEntity(new JsonMessageResponse("El producto '"+producto.getNombre()+"' se ha ingresado correctamente.", "OK"), HttpStatus.OK);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<Producto> getByEmpresaId(@PathVariable("empresaId") int empresaId){
        List<Producto> listaProductos = productoService.getProductosByEmpresaId(empresaId);
        return new ResponseEntity(listaProductos, HttpStatus.OK);
    }

    @GetMapping("/empresa/only_name_and_id/{empresaId}")
    public ResponseEntity<Producto> getByEmpresaIdOnlyNameAndId(@PathVariable("empresaId") int empresaId){
        try {
            List<ProductoNameAndId> listaProductos = productoService.getProductosOnlyNameAndId(empresaId);
            return new ResponseEntity(listaProductos, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(new JsonMessageResponse("Error al buscar los productos.","ERROR"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") Long id){
        try{
            Producto producto = productoService.getProductoById(id).get();
            return new ResponseEntity(producto, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(new JsonMessageResponse("Error al buscar el producto.","ERROR"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody ProductoDto productoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de producto mal escritos o faltantes, revisar antes de guardar el producto.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(productoService.existByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getIdProducto() != id) {
            return new ResponseEntity(new JsonMessageResponse("Ya existe un producto con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }

        Producto producto = productoService.getProductoById(id).get();
        SubtipoProducto subtipoProducto = null;
        MarcaProducto marcaProducto = null;
        if(productoDto.getIdSubtipoProducto() != null){
            subtipoProducto = subtipoProductoService.getSubtipoByIdSubtipo(productoDto.getIdSubtipoProducto()).get();
        }
        if(productoDto.getIdSubtipoProducto() != null){
            marcaProducto = marcaProductoService.getMarcaByIdMarca(productoDto.getIdMarcaProducto()).get();
        }
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        producto.setSubtipoProducto(subtipoProducto);
        producto.setMarcaProducto(marcaProducto);
        productoService.createProducto(producto);

        return new ResponseEntity(new JsonMessageResponse("El producto '"+producto.getNombre()+"' se ha actualizado correctamente.", "OK"), HttpStatus.OK);
    }

    @PutMapping("/change_availability/{id}")
    public ResponseEntity<?> changeActivoOfProducto(@PathVariable("id") Long id){
        try{
            Producto producto = productoService.getProductoById(id).get();
            productoService.changeProductoActivo(producto);
            return new ResponseEntity(new JsonMessageResponse("El producto '" + producto.getNombre() + "' se ha actualizado correctamente.", "OK"), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(new JsonMessageResponse("Ocurrió un error al intentar cambiar el estado del producto.", "ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
