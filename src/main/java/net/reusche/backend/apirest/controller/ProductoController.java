package net.reusche.backend.apirest.controller;

import net.reusche.backend.apirest.dto.ProductoActivo;
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
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ResponseEntity<?> create(@Valid @ModelAttribute ProductoDto productoDto, BindingResult bindingResult, @RequestPart(name = "file", required = false) MultipartFile imagen){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de producto mal escritos o faltantes, revisar antes de guardar el producto.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(productoService.existByNombre(productoDto.getNombre())){
            return new ResponseEntity(new JsonMessageResponse("Ya existe un producto con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        String nombreArchivo = subirImagen(imagen);

        final SubtipoProducto subtipoProducto = productoDto.getSubtipoProducto() > 0 ? new SubtipoProducto(productoDto.getSubtipoProducto()) : null;
        final MarcaProducto marcaProducto = productoDto.getMarcaProducto() > 0 ? new MarcaProducto(productoDto.getMarcaProducto()) : null;
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio(), productoDto.getUrlImagen(), productoDto.isActivo(), new Empresa(productoDto.getEmpresa()), subtipoProducto , marcaProducto);
        if(nombreArchivo != null) producto.setUrlImagen(nombreArchivo);
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
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @ModelAttribute ProductoDto productoDto, BindingResult bindingResult, @RequestPart(name = "file", required = false) MultipartFile imagen){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de producto mal escritos o faltantes, revisar antes de guardar el producto.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(productoService.existByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getIdProducto() != id) {
            return new ResponseEntity(new JsonMessageResponse("Ya existe un producto con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        String nombreArchivo = subirImagen(imagen);

        if(nombreArchivo != null && productoDto.getUrlImagen() != null){
            try{
                Path directorioImagenes = Paths.get("src//main//resources//static/images");
                Path rutaCompleta = Paths.get(directorioImagenes.toFile().getAbsolutePath()+"//"+productoDto.getUrlImagen());
                Files.delete(rutaCompleta);
            }
            catch(Exception ex){
                ex.printStackTrace();
                return new ResponseEntity(new JsonMessageResponse("Error al intentar eliminar imagen anterior", "ERROR"), HttpStatus.OK);
            }
        }

        final SubtipoProducto subtipoProducto = productoDto.getSubtipoProducto() > 0 ? new SubtipoProducto(productoDto.getSubtipoProducto()) : null;
        final MarcaProducto marcaProducto = productoDto.getMarcaProducto() > 0 ? new MarcaProducto(productoDto.getMarcaProducto()) : null;
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio(), productoDto.getUrlImagen(), productoDto.isActivo(), new Empresa(productoDto.getEmpresa()), subtipoProducto , marcaProducto);
        producto.setIdProducto(productoDto.getIdProducto());
        if(nombreArchivo != null) producto.setUrlImagen(nombreArchivo);
        productoService.createProducto(producto);

        return new ResponseEntity(new JsonMessageResponse("El producto '"+producto.getNombre()+"' se ha actualizado correctamente.", "OK"), HttpStatus.OK);
    }

    @PutMapping("/change_availability")
    public ResponseEntity<?> changeActivoOfProducto(@RequestBody List<ProductoActivo> productosLista){
        try{
            for (ProductoActivo productoActivo: productosLista) {
                productoService.changeProductoActivo(productoActivo);
            }
            return new ResponseEntity(new JsonMessageResponse("Se ha actualizado el estado de/los producto(s).", "OK"), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(new JsonMessageResponse("Ocurrió un error al intentar cambiar el estado de/los productos.", "ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String subirImagen(@RequestPart(name = "file", required = false) MultipartFile imagen){
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String fechaActual = dateFormatter.format(new Date());

        if(imagen != null){
            if(!imagen.isEmpty()){
                Path directorioImagenes = Paths.get("src//main//resources//static/images");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

                try {
                    byte[] bytesImg = imagen.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+fechaActual+"."+FilenameUtils.getExtension(imagen.getOriginalFilename()));
                    Files.write(rutaCompleta, bytesImg);
                    return (fechaActual+"."+FilenameUtils.getExtension(imagen.getOriginalFilename()));
                }
                catch (IOException ex){
                    ex.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }
}
