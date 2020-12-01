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
    private int numero;
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


    public static void cargarTabla(){
        try {
            FileInputStream fis = null;
            fis = new FileInputStream(new File("E:\\Projects\\Proyecto Celiacos\\Excels\\Cereales.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();


            HashMap<String,Integer> campos = new HashMap<>();
            //sector leer la fila 5 para saber que columnas son las que hay
            HSSFRow fila5 = sheet.getRow(4);
            for(Cell cell : fila5){
                if (formulaEvaluator.evaluateInCell(cell).getCellType() == CellType.STRING){
                    if(!cell.getStringCellValue().equals("Energía"))
                        campos.put(cell.getStringCellValue().trim(),cell.getColumnIndex());
                    else  if(!campos.containsKey("Energía"))
                        campos.put(cell.getStringCellValue().trim(),cell.getColumnIndex());
                }
            }

            //busca donde comienzan los alimentos
            int i = 8;




            for(;i<sheet.getPhysicalNumberOfRows();i++){ //cargar los N alimentos
                HSSFRow filaActual = sheet.getRow(i);



                if(filaActual.getCell(campos.get("Nº")) != null && filaActual.getCell(campos.get("Nº")).getCellType() == CellType.NUMERIC) //si existe la fila
                {
                    Alimento nuevoAlimento = new Alimento();

                    if(campos.containsKey("Nº"))
                        if(filaActual.getCell(campos.get("Nº")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.numero = (int) filaActual.getCell(campos.get("Nº")).getNumericCellValue();

                    if(campos.containsKey("Alimento"))
                        if(filaActual.getCell(campos.get("Alimento")).getCellType() == CellType.STRING)
                            nuevoAlimento.nombre = filaActual.getCell(campos.get("Alimento")).getStringCellValue();
                        else nuevoAlimento.nombre = ""; //no deberia pasar
//TODO agregar clasificacion
                    if(campos.containsKey("Género - especie - variedad"))
                        if(filaActual.getCell(campos.get("Género - especie - variedad")).getCellType() == CellType.STRING)
                            nuevoAlimento.genero_especie_variedad = filaActual.getCell(campos.get("Género - especie - variedad")).getStringCellValue();
                        else nuevoAlimento.genero_especie_variedad = "";

                    if(campos.containsKey("Energía"))
                        if(filaActual.getCell(campos.get("Energía")).getCellType() == CellType.NUMERIC)
                    nuevoAlimento.energia_kJ = filaActual.getCell(campos.get("Energía")).getNumericCellValue();

                    if(campos.containsKey("Agua"))
                        if(filaActual.getCell(campos.get("Agua")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.agua = filaActual.getCell(campos.get("Agua")).getNumericCellValue();

                    if(campos.containsKey("Proteínas"))
                        if(filaActual.getCell(campos.get("Proteínas")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.proteina = filaActual.getCell(campos.get("Proteínas")).getNumericCellValue();

                    if(campos.containsKey("Grasa Total"))
                        if(filaActual.getCell(campos.get("Grasa Total")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.grasa_total = filaActual.getCell(campos.get("Grasa Total")).getNumericCellValue();

                    if(campos.containsKey("Carbohidratos totales"))
                        if(filaActual.getCell(campos.get("Carbohidratos totales")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.carbohidrato_total = filaActual.getCell(campos.get("Carbohidratos totales")).getNumericCellValue();

                    if(campos.containsKey("Carbohidratos disponibles"))
                        if(filaActual.getCell(campos.get("Carbohidratos disponibles")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.carbohidrato_disponible = filaActual.getCell(campos.get("Carbohidratos disponibles")).getNumericCellValue();

                    if(campos.containsKey("Fibra dietética"))
                        if(filaActual.getCell(campos.get("Fibra dietética")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.fibra_dietetica = filaActual.getCell(campos.get("Fibra dietética")).getNumericCellValue();

                    if(campos.containsKey("Cenizas"))
                        if(filaActual.getCell(campos.get("Cenizas")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.ceniza = filaActual.getCell(campos.get("Cenizas")).getNumericCellValue();

                    if(campos.containsKey("Sodio"))
                        if(filaActual.getCell(campos.get("Sodio")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.sodio = filaActual.getCell(campos.get("Sodio")).getNumericCellValue();

                    if(campos.containsKey("Potasio"))
                        if(filaActual.getCell(campos.get("Potasio")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.potasio = filaActual.getCell(campos.get("Potasio")).getNumericCellValue();

                    if(campos.containsKey("Calcio"))
                        if(filaActual.getCell(campos.get("Calcio")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.calcio = filaActual.getCell(campos.get("Calcio")).getNumericCellValue();

                    if(campos.containsKey("Fósforo"))
                        if(filaActual.getCell(campos.get("Fósforo")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.fosforo = filaActual.getCell(campos.get("Fósforo")).getNumericCellValue();

                    if(campos.containsKey("Hierro"))
                        if(filaActual.getCell(campos.get("Hierro")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.hierro = filaActual.getCell(campos.get("Hierro")).getNumericCellValue();

                    if(campos.containsKey("Zinc"))
                        if(filaActual.getCell(campos.get("Zinc")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.zinc = filaActual.getCell(campos.get("Zinc")).getNumericCellValue();

                    if(campos.containsKey("Tiamina"))
                        if(filaActual.getCell(campos.get("Tiamina")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.tiamina = filaActual.getCell(campos.get("Tiamina")).getNumericCellValue();

                    if(campos.containsKey("Rivoflavina"))
                        if(filaActual.getCell(campos.get("Rivoflavina")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.rivoflavina = filaActual.getCell(campos.get("Rivoflavina")).getNumericCellValue();

                    if(campos.containsKey("Niacina"))
                        if(filaActual.getCell(campos.get("Niacina")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.niacina = filaActual.getCell(campos.get("Niacina")).getNumericCellValue();

                    if(campos.containsKey("Vitamina C"))
                        if(filaActual.getCell(campos.get("Vitamina C")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.vitamina_c = filaActual.getCell(campos.get("Vitamina C")).getNumericCellValue();

                    if(campos.containsKey("Ac. grasos saturados"))
                        if(filaActual.getCell(campos.get("Ac. grasos saturados")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.acidos_grasos_saturados = filaActual.getCell(campos.get("Ac. grasos saturados")).getNumericCellValue();

                    if(campos.containsKey("Ac. grasos monoinsaturados"))
                        if(filaActual.getCell(campos.get("Ac. grasos monoinsaturados")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.acidos_grasos_monoinsaturados = filaActual.getCell(campos.get("Ac. grasos monoinsaturados")).getNumericCellValue();

                    if(campos.containsKey("Ac. grasos poliinsaturados"))
                        if(filaActual.getCell(campos.get("Ac. grasos poliinsaturados")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.acidos_grasos_poliinsaturados = filaActual.getCell(campos.get("Ac. grasos poliinsaturados")).getNumericCellValue();

                    if(campos.containsKey("Colesterol"))
                        if(filaActual.getCell(campos.get("Colesterol")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.colesterol = filaActual.getCell(campos.get("Colesterol")).getNumericCellValue();

                    System.out.println("juju");
                    System.out.println(nuevoAlimento);

                }

            }

        }
         catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
