package com.mau.spring.service;


import com.mau.spring.model.Alimento;
import com.mau.spring.model.Receta;
import com.mau.spring.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> getAll(String name){
        if(isNull(name))
            return recetaRepository.findAll();
        else
            return recetaRepository.findByNombre(name);
    }

    public void addReceta(Receta nuevaReceta){
        nuevaReceta.setIdReceta(null);
        recetaRepository.save(nuevaReceta);
    }
    public void modificarReceta(Receta nuevaReceta){
        recetaRepository.save(nuevaReceta);
    }

    public void deleteReceta(Integer idReceta) {
        recetaRepository.deleteById(idReceta);
    }
}
