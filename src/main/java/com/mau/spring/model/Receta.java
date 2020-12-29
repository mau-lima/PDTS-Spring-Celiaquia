package com.mau.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Ingrediente> ingredientes;

    private String instrucciones;
}
