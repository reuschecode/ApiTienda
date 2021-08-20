package net.reusche.backend.apirest.controller;

import net.reusche.backend.apirest.dto.MarcaProductoDto;
import net.reusche.backend.apirest.entity.Empresa;
import net.reusche.backend.apirest.entity.MarcaProducto;
import net.reusche.backend.apirest.service.MarcaProductoService;
import net.reusche.backend.apirest.util.JsonMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin("http://localhost:4200")
public class MarcaProductoController {

    @Autowired
    MarcaProductoService marcaProductoService;

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<MarcaProducto> getByEmpresaId(@PathVariable("empresaId") int empresaId){
        List<MarcaProducto> listaMarca = marcaProductoService.getMarcasByEmpresaId(empresaId);
        return new ResponseEntity(listaMarca, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody MarcaProductoDto marcaProductoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de la marca mal escritos, revisar antes de guardar.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(marcaProductoService.existByNombre(marcaProductoDto.getNombre())){
            return new ResponseEntity(new JsonMessageResponse("Ya existe un producto con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        MarcaProducto marcaProducto = new MarcaProducto(marcaProductoDto.getNombre(), new Empresa(marcaProductoDto.getIdEmpresa()));
        marcaProductoService.createMarca(marcaProducto);

        return new ResponseEntity(new JsonMessageResponse("La marca '"+marcaProducto.getNombre()+"' se ha ingresado correctamente.", "OK"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id, @Valid @RequestBody MarcaProductoDto marcaProductoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de la marca mal escritos, revisar antes de guardar.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(marcaProductoService.existByNombre(marcaProductoDto.getNombre())){
            return new ResponseEntity(new JsonMessageResponse("Ya existe un producto con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        MarcaProducto marcaProducto = new MarcaProducto(marcaProductoDto.getNombre(), new Empresa(marcaProductoDto.getIdEmpresa()));
        marcaProducto.setIdMarcaProducto(id);
        marcaProductoService.createMarca(marcaProducto);

        return new ResponseEntity(new JsonMessageResponse("La marca '"+marcaProducto.getNombre()+"' se ha actualizado correctamente.", "OK"), HttpStatus.OK);
    }

}
