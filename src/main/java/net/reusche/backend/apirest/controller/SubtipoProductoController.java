package net.reusche.backend.apirest.controller;

import net.reusche.backend.apirest.dto.MarcaProductoDto;
import net.reusche.backend.apirest.dto.SubtipoProductoDto;
import net.reusche.backend.apirest.entity.Empresa;
import net.reusche.backend.apirest.entity.MarcaProducto;
import net.reusche.backend.apirest.entity.SubtipoProducto;
import net.reusche.backend.apirest.entity.TipoProducto;
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
@RequestMapping("/api/subtipos")
@CrossOrigin("http://localhost:4200")
public class SubtipoProductoController {

    @Autowired
    SubtipoProductoService subtipoProductoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody SubtipoProductoDto subtipoProductoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de el subtipo mal escritos, revisar antes de guardar.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(subtipoProductoService.existByNombre(subtipoProductoDto.getNombre())){
            return new ResponseEntity(new JsonMessageResponse("Ya existe un subtipo con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        SubtipoProducto subtipoProducto = new SubtipoProducto(subtipoProductoDto.getNombre(), new TipoProducto(subtipoProductoDto.getIdTipo()));
        subtipoProductoService.createSubtipo(subtipoProducto);

        return new ResponseEntity(new JsonMessageResponse("El subtipo '"+subtipoProducto.getNombre()+"' se ha ingresado correctamente.", "OK"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id, @Valid @RequestBody SubtipoProductoDto subtipoProductoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de el subtipo mal escritos, revisar antes de guardar.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(subtipoProductoService.existByNombre(subtipoProductoDto.getNombre())){
            return new ResponseEntity(new JsonMessageResponse("Ya existe un subtipo con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        SubtipoProducto subtipoProducto = new SubtipoProducto(subtipoProductoDto.getNombre(), new TipoProducto(subtipoProductoDto.getIdTipo()));
        subtipoProducto.setIdSubtipoProducto(id);
        subtipoProductoService.createSubtipo(subtipoProducto);

        return new ResponseEntity(new JsonMessageResponse("El subtipo '"+subtipoProducto.getNombre()+"' se ha actualizado correctamente.", "OK"), HttpStatus.OK);
    }
}
