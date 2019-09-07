package com.estetiCloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.service.IProfesionalService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/Profesional")

public class ProfesionalController {
	
	@Autowired
    private IProfesionalService profesionalService;

	@GetMapping(value = "/listaProfesional", produces = {"application/json"})
    public ResponseEntity<List<Profesional>> findAll() {
		List<Profesional>lista = profesionalService.findAll();
		
		
    	if (lista.isEmpty()) {
			return new ResponseEntity<List<Profesional>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Profesional>>(lista, HttpStatus.OK); 
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
    
    
    @PutMapping(value ="/updateProfesional/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Profesional profesional, @PathVariable Long id) {
    	Profesional ProfesionalActual=profesionalService.findOne(id);
    	

        Map<String,Object> response =new HashMap<String, Object>();

        if(ProfesionalActual==null) {
            response.put("mensaje","No se pudo editar, el funcionario con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {
        	ProfesionalActual.setId(profesional.getId());
        	ProfesionalActual.setNombre(profesional.getNombre());
        	ProfesionalActual.setApellido(profesional.getApellido());
        	ProfesionalActual.setTelefono(profesional.getTelefono());
        	ProfesionalActual.setEmail(profesional.getEmail());
        	profesionalService.save(ProfesionalActual);

        }catch(DataAccessException e) {
            response.put("mensaje","Error al actualizar el Profesional en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El Profesional ha sido actualizado con éxito!");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
}
