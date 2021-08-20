package net.reusche.backend.apirest.controller;

import net.reusche.backend.apirest.dto.SubtipoProductoDto;
import net.reusche.backend.apirest.dto.TipoProductoDto;
import net.reusche.backend.apirest.entity.Empresa;
import net.reusche.backend.apirest.entity.SubtipoProducto;
import net.reusche.backend.apirest.entity.TipoProducto;
import net.reusche.backend.apirest.entity.interfaces.TiposWithSubtipos;
import net.reusche.backend.apirest.service.SubtipoProductoService;
import net.reusche.backend.apirest.service.TipoProductoService;
import net.reusche.backend.apirest.util.JsonMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin("http://localhost:4200")
public class TipoProductoController {

    @Autowired
    TipoProductoService tipoProductoService;

    @Autowired
    SubtipoProductoService subtipoProductoService;

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<TipoProducto> getByEmpresaId(@PathVariable("empresaId") int empresaId){
        List<TiposWithSubtipos> listaTipos = tipoProductoService.getTiposByIdEmpresa(empresaId);
        return new ResponseEntity(listaTipos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody TipoProductoDto tipoProductoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de el tipo mal escritos, revisar antes de guardar.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(tipoProductoService.existByNombre(tipoProductoDto.getNombre())){
            return new ResponseEntity(new JsonMessageResponse("Ya existe un tipo con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        TipoProducto tipoProducto = new TipoProducto(tipoProductoDto.getNombre(), new Empresa(tipoProductoDto.getIdEmpresa()));
        tipoProductoService.createTipoProducto(tipoProducto);
        SubtipoProducto subtipoProducto = new SubtipoProducto(tipoProductoDto.getNombre(), tipoProducto);
        subtipoProductoService.createSubtipo(subtipoProducto);

        return new ResponseEntity(new JsonMessageResponse("El tipo '"+tipoProducto.getNombre()+"' se ha ingresado correctamente.", "OK"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @Valid @RequestBody TipoProductoDto tipoProductoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new JsonMessageResponse("Datos de el tipo mal escritos, revisar antes de guardar.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        if(tipoProductoService.existByNombre(tipoProductoDto.getNombre())){
            return new ResponseEntity(new JsonMessageResponse("Ya existe un tipo con ese nombre.", "ERROR"), HttpStatus.BAD_REQUEST);
        }
        TipoProducto tipoProducto = new TipoProducto(tipoProductoDto.getNombre(), new Empresa(tipoProductoDto.getIdEmpresa()));
        tipoProducto.setIdTipoProducto(id);
        tipoProductoService.createTipoProducto(tipoProducto);

        return new ResponseEntity(new JsonMessageResponse("El tipo '"+tipoProducto.getNombre()+"' se ha actualizado correctamente.", "OK"), HttpStatus.OK);
    }
}
