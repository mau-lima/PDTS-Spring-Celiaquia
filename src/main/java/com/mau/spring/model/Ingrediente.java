package com.mau.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ingrediente {
    @Id
    private int idIngrediente;
    @OneToOne
    private Alimento alimento;
    private double cantidad;
    private UnidadDeMedida unidadDeMedida;
}
