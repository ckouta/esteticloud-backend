package com.estetiCloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.service.IProfesionalService;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProfesionalController {
	
	@Autowired
    private IProfesionalService profesionalService;

    @RequestMapping(value = "/listaProfesional", method = RequestMethod.GET )
    public List<Profesional> findAll() {
        List<Profesional> listaProfesional = profesionalService.findAll();
        return listaProfesional;
    }
    @PostMapping(value= "/save")
    public ResponseEntity<Profesional> create(@RequestBody Profesional profesional,BindingResult bindingResult){


        try {
        	profesionalService.save(profesional);
        }catch(DataAccessException e) {
            return new ResponseEntity<Profesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Profesional>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/DeleteProfesional/{id}",  method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	profesionalService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar el profesional de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El profesional fue eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    //pendiente
    /*
    @PutMapping(value ="/updateProfesional/{id}")
    public ResponseEntity<?> update(@RequestBody Profesional profesional, @PathVariable Long id) {
        Funcionario funcionarioActual=funcionarioService.findById(id);
        Funcionario funcionarioUpdated=null;

        Map<String,Object> response =new HashMap<String, Object>();

        if(funcionarioActual==null) {
            response.put("mensaje","No se pudo editar, el funcionario con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {
            funcionarioActual.setId(funcionario.getId());
            funcionarioActual.setNombre(funcionario.getNombre());
            funcionarioActual.setApellido(funcionario.getApellido());
            funcionarioActual.setCargo(funcionario.getCargo());


            funcionarioUpdated=funcionarioService.save(funcionarioActual);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al actualizar el funcionario en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El funcionario ha sido actualizado con éxito!");
        response.put("funcionario",funcionarioUpdated);

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }*/
}
