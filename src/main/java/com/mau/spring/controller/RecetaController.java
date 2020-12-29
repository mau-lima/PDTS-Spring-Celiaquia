package com.mau.spring.controller;

import com.mau.spring.model.Alimento;
import com.mau.spring.model.Receta;
import com.mau.spring.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receta")
public class RecetaController {
    private final RecetaService recetaService;

    @Autowired
    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping("/")
    public void addReceta(@RequestBody(required = true) Receta nuevaReceta){
        recetaService.addReceta(nuevaReceta);
    }

    @PostMapping("/modificar")
    public void modificarReceta(@RequestBody(required = true) Receta nuevaReceta){
        recetaService.modificarReceta(nuevaReceta);
    }

    @GetMapping("/")
    public List<Receta> getAll(@RequestParam(required = false) String name){
        return recetaService.getAll(name);
    }

    @DeleteMapping("/{idReceta}")
    public void deleteReceta(@PathVariable Integer idReceta){
        recetaService.deleteReceta(idReceta);
    }


}
