package com.mau.spring.controller;


import com.mau.spring.model.Alimento;
import com.mau.spring.model.AccesibleDTO;
import com.mau.spring.model.AlimentoNotFoundException;
import com.mau.spring.service.AlimentoService;
import com.mau.spring.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/alimento") //prefijo para todas las requests. o sea las que estan mapeadas  a / en realidad estan en /alimento/
public class AlimentoController { //el controller es el frente al exterior, llama al service para interactuar con el modelo/ la persistencia

    private final AlimentoService alimentoService;

    @Autowired
    public AlimentoController(AlimentoService alimentoService) {
        this.alimentoService = alimentoService;
    }

    @GetMapping("/")
    public List<Alimento> getAll(@RequestParam(required = false) String name){
        return alimentoService.getAll(name);
    }

    @GetMapping("/{alimentoId}")
    public Alimento getAlimentoById(@PathVariable Integer alimentoId){
        try {
            return alimentoService.get(alimentoId);
        } catch (AlimentoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El alimento solicitado no existe");
        }
    }

    @PostMapping("/")
    public void addAlimento(@RequestBody Alimento nuevoAlimento){
        alimentoService.addAlimento(nuevoAlimento);
    }

    @PostMapping("/cargarTablas")
    public void cargarTablas(){ //TODO: Parametrizar de donde vienen los .xls
        alimentoService.cargarTablas();
    }

    @PostMapping("/setAccesible")
    public void setAccesible(@RequestBody AccesibleDTO accesibleDTO){
        try {
            alimentoService.setAccesible(accesibleDTO);
        } catch (AlimentoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El alimento a modificar no existe");
        }
    }

    @PostMapping("/setImagen")
    public void setImagen(int numero, @RequestParam("image") MultipartFile multipartFile){
        try {
            String[] temp = StringUtils.cleanPath(multipartFile.getOriginalFilename()).split("\\.");
            String extension = temp[temp.length-1];

            String fileName = ""+numero+"."+extension;
            String uploadDir = "public/img/alimentos/";

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            alimentoService.setImagen(numero, fileName);
        } catch (AlimentoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El alimento a modificar no existe");
        }
        catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "La imagen para este alimento no pudo ser guardada");
        }
    }

}
