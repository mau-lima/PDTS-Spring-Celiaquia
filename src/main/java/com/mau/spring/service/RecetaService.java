package com.mau.spring.service;


import com.mau.spring.model.Receta;
import com.mau.spring.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public void addReceta(Receta nuevaReceta){
        recetaRepository.save(nuevaReceta);
    }
}
