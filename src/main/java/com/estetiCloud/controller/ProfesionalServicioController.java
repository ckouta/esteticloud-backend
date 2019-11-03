package com.estetiCloud.controller;

import java.util.ArrayList;
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
import com.estetiCloud.models.entity.Servicio;
import com.estetiCloud.models.entity.Servicio_ofrecido;
import com.estetiCloud.models.service.IServicioOfrecidoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ps")

public class ProfesionalServicioController {
	
	@Autowired
    private IServicioOfrecidoService servicioOfrecidoService;

	@GetMapping(value = "/listar")
    public ResponseEntity<List<Servicio_ofrecido>> findAll() {
		List<Servicio_ofrecido>lista = servicioOfrecidoService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<Servicio_ofrecido>>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Servicio_ofrecido>>(lista, HttpStatus.OK); 
    }
	

    @PostMapping(value= "/save")
    public ResponseEntity<Servicio_ofrecido> create(@RequestBody Servicio_ofrecido profesional,BindingResult bindingResult){


        try {
        	servicioOfrecidoService.save(profesional);
        }catch(DataAccessException e) {
            return new ResponseEntity<Servicio_ofrecido>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Servicio_ofrecido>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/delete/{id}",  method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	servicioOfrecidoService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar el profesional de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    
	@GetMapping(value = "/listarPS")
    public ResponseEntity<List<Servicio>> findByServicio(@RequestBody Profesional profesional) {
		List<Servicio> aux = new ArrayList<Servicio>();
    	for(Servicio_ofrecido Profesional : servicioOfrecidoService.buscarPorProfesional(profesional)) {
			aux.add(Profesional.getServicio());
		}
		return new ResponseEntity<List<Servicio>>(aux, HttpStatus.OK); 
    }
	@GetMapping(value = "/listarSP")
    public ResponseEntity<List<Profesional>> findByServicio(@RequestBody Servicio servicio) {
		List<Profesional> aux = new ArrayList<Profesional>();
    	for(Servicio_ofrecido Servicio : servicioOfrecidoService.buscarPorServicio(servicio)) {
			aux.add(Servicio.getProfesional());
		}
		return new ResponseEntity<List<Profesional>>(aux, HttpStatus.OK); 
    }
}
