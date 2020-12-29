package com.mau.spring.model;

import com.mau.spring.repository.AlimentoRepository;
import com.mau.spring.service.AlimentoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //entidad de persistencia
public class Alimento {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;
    private String nombre;
    private String clasificacion; // carnes, cereales, etc. Son las diferentes tablas
    private String genero_especie_variedad;//algunas tablas de alimento lo tienen
    private double energia_kJ;
    private double agua;
    private double proteina;
    private double grasa_total;
    private double carbohidrato_total;
    private double carbohidrato_disponible;
    private double fibra_dietetica;
    private double ceniza;
    private double sodio;
    private double potasio;
    private double calcio;
    private double fosforo;
    private double hierro;
    private double zinc;
    private double tiamina;
    private double rivoflavina;
    private double niacina;
    private double vitamina_c;
    private double acidos_grasos_saturados;
    private double acidos_grasos_monoinsaturados;
    private double acidos_grasos_poliinsaturados;
    private double colesterol;

}
