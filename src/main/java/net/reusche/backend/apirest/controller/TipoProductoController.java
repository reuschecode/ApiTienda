package net.reusche.backend.apirest.controller;

import net.reusche.backend.apirest.entity.SubtipoProducto;
import net.reusche.backend.apirest.entity.TipoProducto;
import net.reusche.backend.apirest.entity.interfaces.TiposWithSubtipos;
import net.reusche.backend.apirest.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin("http://localhost:4200")
public class TipoProductoController {

    @Autowired
    TipoProductoService tipoProductoService;

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<TipoProducto> getByEmpresaId(@PathVariable("empresaId") int empresaId){
        List<TiposWithSubtipos> listaTipos = tipoProductoService.getTiposByIdEmpresa(empresaId);
        return new ResponseEntity(listaTipos, HttpStatus.OK);
    }

}
