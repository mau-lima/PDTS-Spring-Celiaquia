package com.mau.spring.repository;

import com.mau.spring.model.Receta;
import com.mau.spring.model.UnidadDeMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadesRepository extends JpaRepository<UnidadDeMedida,Integer> {
}
