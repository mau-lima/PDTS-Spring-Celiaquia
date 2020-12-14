package com.mau.spring.repository;


import com.mau.spring.model.Alimento;
import com.mau.spring.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer> {
}
