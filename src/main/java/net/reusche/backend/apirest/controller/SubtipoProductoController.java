package net.reusche.backend.apirest.controller;

import net.reusche.backend.apirest.entity.SubtipoProducto;
import net.reusche.backend.apirest.service.SubtipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtipos")
@CrossOrigin("http://localhost:4200")
public class SubtipoProductoController {

    @Autowired
    SubtipoProductoService subtipoProductoService;

}
