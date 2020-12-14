package com.mau.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Receta {
    @Id
    private int idReceta;
    private String nombre;
    private String descripcion;
    @OneToMany
    private Set<Ingrediente> ingredientes;
}
