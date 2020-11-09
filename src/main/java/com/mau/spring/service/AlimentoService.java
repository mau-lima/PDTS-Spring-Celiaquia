package com.mau.spring.service;

import com.mau.spring.model.Alimento;
import com.mau.spring.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class AlimentoService {
    private final AlimentoRepository alimentoRepository;

    @Autowired
    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public void addAlimento(Alimento nuevaAlimento) {
        alimentoRepository.save(nuevaAlimento);
    }

    public List<Alimento> getAll(String name){
        if(isNull(name))
            return alimentoRepository.findAll();
        else
            return alimentoRepository.findByNombre(name);
    }
}
