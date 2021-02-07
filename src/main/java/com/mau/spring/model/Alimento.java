package com.mau.spring.model;

import com.mau.spring.repository.AlimentoRepository;
import com.mau.spring.service.AlimentoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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

@Getter
@NoArgsConstructor
@Entity //entidad de persistencia
public class Alimento extends AlimentoExcel {
    public void setEsAccesible(Boolean esAccesible) {
        this.esAccesible = esAccesible;
    }

    private Boolean esAccesible = null;


    public Alimento(Integer numero, String nombre, String clasificacion, String genero_especie_variedad, Double energia_kJ, Double agua, Double proteina, Double grasa_total, Double carbohidrato_total, Double carbohidrato_disponible, Double fibra_dietetica, Double ceniza, Double sodio, Double potasio, Double calcio, Double fosforo, Double hierro, Double zinc, Double tiamina, Double rivoflavina, Double niacina, Double vitamina_c, Double acidos_grasos_saturados, Double acidos_grasos_monoinsaturados, Double acidos_grasos_poliinsaturados, Double colesterol) {
        super(numero,  nombre,  clasificacion,  genero_especie_variedad,  energia_kJ,  agua,  proteina,  grasa_total,  carbohidrato_total,  carbohidrato_disponible,  fibra_dietetica,  ceniza,  sodio,  potasio,  calcio,  fosforo,  hierro,  zinc,  tiamina,  rivoflavina,  niacina,  vitamina_c,  acidos_grasos_saturados,  acidos_grasos_monoinsaturados,  acidos_grasos_poliinsaturados,  colesterol);
    }
}
