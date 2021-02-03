package com.mau.spring.service;

import com.mau.spring.model.AccesibleDTO;
import com.mau.spring.model.Alimento;
import com.mau.spring.model.AlimentoNotFoundException;
import com.mau.spring.repository.AlimentoRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    public void setAccesible(AccesibleDTO accesibleDTO) throws AlimentoNotFoundException {
        Optional<Alimento> optionalAlimento = alimentoRepository.findById(accesibleDTO.getNumero());
        if(optionalAlimento.isPresent()){
            Alimento alimentoAModificar =optionalAlimento.get();

            alimentoAModificar.setEsAccesible(accesibleDTO.isEsAccesible());
            alimentoRepository.save(alimentoAModificar);
        }
        else{
            throw new AlimentoNotFoundException();
        }

    }

    public void cargarTablas() {
        try {
            FileInputStream fis =  new FileInputStream(new File("E:\\Projects\\Proyecto Celiacos\\Excels\\Cereales.xls"));
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
                            nuevoAlimento.setNumero( (int) filaActual.getCell(campos.get("Nº")).getNumericCellValue());

                    if(campos.containsKey("Alimento"))
                        if(filaActual.getCell(campos.get("Alimento")).getCellType() == CellType.STRING)
                            nuevoAlimento.setNombre(filaActual.getCell(campos.get("Alimento")).getStringCellValue());
                        else nuevoAlimento.setNombre(""); //no deberia pasar
//TODO agregar clasificacion
                    if(campos.containsKey("Género - especie - variedad"))
                        if(filaActual.getCell(campos.get("Género - especie - variedad")).getCellType() == CellType.STRING)
                            nuevoAlimento.setGenero_especie_variedad(filaActual.getCell(campos.get("Género - especie - variedad")).getStringCellValue());
                        else nuevoAlimento.setGenero_especie_variedad( "");

                    if(campos.containsKey("Energía"))
                        if(filaActual.getCell(campos.get("Energía")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setEnergia_kJ (filaActual.getCell(campos.get("Energía")).getNumericCellValue());

                    if(campos.containsKey("Agua"))
                        if(filaActual.getCell(campos.get("Agua")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setAgua( filaActual.getCell(campos.get("Agua")).getNumericCellValue());

                    if(campos.containsKey("Proteínas"))
                        if(filaActual.getCell(campos.get("Proteínas")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setProteina( filaActual.getCell(campos.get("Proteínas")).getNumericCellValue());

                    if(campos.containsKey("Grasa Total"))
                        if(filaActual.getCell(campos.get("Grasa Total")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setGrasa_total(filaActual.getCell(campos.get("Grasa Total")).getNumericCellValue());

                    if(campos.containsKey("Carbohidratos totales"))
                        if(filaActual.getCell(campos.get("Carbohidratos totales")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setCarbohidrato_total(filaActual.getCell(campos.get("Carbohidratos totales")).getNumericCellValue());

                    if(campos.containsKey("Carbohidratos disponibles"))
                        if(filaActual.getCell(campos.get("Carbohidratos disponibles")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setCarbohidrato_disponible( filaActual.getCell(campos.get("Carbohidratos disponibles")).getNumericCellValue());

                    if(campos.containsKey("Fibra dietética"))
                        if(filaActual.getCell(campos.get("Fibra dietética")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setFibra_dietetica( filaActual.getCell(campos.get("Fibra dietética")).getNumericCellValue());

                    if(campos.containsKey("Cenizas"))
                        if(filaActual.getCell(campos.get("Cenizas")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setCeniza( filaActual.getCell(campos.get("Cenizas")).getNumericCellValue());

                    if(campos.containsKey("Sodio"))
                        if(filaActual.getCell(campos.get("Sodio")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setSodio( filaActual.getCell(campos.get("Sodio")).getNumericCellValue());

                    if(campos.containsKey("Potasio"))
                        if(filaActual.getCell(campos.get("Potasio")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setPotasio(filaActual.getCell(campos.get("Potasio")).getNumericCellValue());

                    if(campos.containsKey("Calcio"))
                        if(filaActual.getCell(campos.get("Calcio")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setCalcio( filaActual.getCell(campos.get("Calcio")).getNumericCellValue());

                    if(campos.containsKey("Fósforo"))
                        if(filaActual.getCell(campos.get("Fósforo")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setFosforo(filaActual.getCell(campos.get("Fósforo")).getNumericCellValue());

                    if(campos.containsKey("Hierro"))
                        if(filaActual.getCell(campos.get("Hierro")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setHierro( filaActual.getCell(campos.get("Hierro")).getNumericCellValue());

                    if(campos.containsKey("Zinc"))
                        if(filaActual.getCell(campos.get("Zinc")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setZinc( filaActual.getCell(campos.get("Zinc")).getNumericCellValue());

                    if(campos.containsKey("Tiamina"))
                        if(filaActual.getCell(campos.get("Tiamina")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setTiamina( filaActual.getCell(campos.get("Tiamina")).getNumericCellValue());

                    if(campos.containsKey("Rivoflavina"))
                        if(filaActual.getCell(campos.get("Rivoflavina")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setRivoflavina(filaActual.getCell(campos.get("Rivoflavina")).getNumericCellValue());

                    if(campos.containsKey("Niacina"))
                        if(filaActual.getCell(campos.get("Niacina")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setNiacina( filaActual.getCell(campos.get("Niacina")).getNumericCellValue());

                    if(campos.containsKey("Vitamina C"))
                        if(filaActual.getCell(campos.get("Vitamina C")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setVitamina_c(filaActual.getCell(campos.get("Vitamina C")).getNumericCellValue());

                    if(campos.containsKey("Ac. grasos saturados"))
                        if(filaActual.getCell(campos.get("Ac. grasos saturados")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setAcidos_grasos_saturados(filaActual.getCell(campos.get("Ac. grasos saturados")).getNumericCellValue());

                    if(campos.containsKey("Ac. grasos monoinsaturados"))
                        if(filaActual.getCell(campos.get("Ac. grasos monoinsaturados")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setAcidos_grasos_monoinsaturados( filaActual.getCell(campos.get("Ac. grasos monoinsaturados")).getNumericCellValue());

                    if(campos.containsKey("Ac. grasos poliinsaturados"))
                        if(filaActual.getCell(campos.get("Ac. grasos poliinsaturados")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setAcidos_grasos_poliinsaturados( filaActual.getCell(campos.get("Ac. grasos poliinsaturados")).getNumericCellValue());

                    if(campos.containsKey("Colesterol"))
                        if(filaActual.getCell(campos.get("Colesterol")).getCellType() == CellType.NUMERIC)
                            nuevoAlimento.setColesterol(filaActual.getCell(campos.get("Colesterol")).getNumericCellValue());

                    this.addAlimento(nuevoAlimento);

                }

            }

        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Alimento get(Integer alimentoId) throws AlimentoNotFoundException {

        Optional<Alimento> alimento = alimentoRepository.findById(alimentoId);
        if(alimento.isPresent())
            return alimento.get();
        else throw new AlimentoNotFoundException();
    }
}
