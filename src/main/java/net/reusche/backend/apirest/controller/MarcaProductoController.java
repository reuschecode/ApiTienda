package net.reusche.backend.apirest.controller;

import net.reusche.backend.apirest.entity.MarcaProducto;
import net.reusche.backend.apirest.service.MarcaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
