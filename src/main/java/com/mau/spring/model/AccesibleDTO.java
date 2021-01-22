package com.mau.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

//TODO revisar nombre
@Data
@AllArgsConstructor
public class AccesibleDTO {
    private int numero
            ;
    private boolean esAccesible;
}
