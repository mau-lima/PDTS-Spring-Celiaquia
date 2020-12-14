package com.mau.spring.controller;

import com.mau.spring.model.Receta;
import com.mau.spring.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receta")
public class RecetaController {
    private final RecetaService recetaService;

    @Autowired
    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping("/")
    public void addReceta(@RequestBody Receta nuevaReceta){
        recetaService.addReceta(nuevaReceta);
    }
}
