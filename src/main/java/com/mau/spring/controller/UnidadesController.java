package com.mau.spring.controller;

import com.mau.spring.model.UnidadDeMedida;
import com.mau.spring.service.UnidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadesController {
    private final UnidadesService unidadesService;

    @Autowired
    public UnidadesController(UnidadesService unidadesService) {
        this.unidadesService = unidadesService;
    }

    @GetMapping("/")
    public List<UnidadDeMedida> getAll() {
        return unidadesService.getAll();
    }

    @PostMapping("/")
    public void addUnidad(@RequestBody UnidadDeMedida nuevaUnidad) {
        unidadesService.addUnidad(nuevaUnidad);
    }
}
