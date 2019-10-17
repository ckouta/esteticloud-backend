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

import com.estetiCloud.models.entity.Servicio;
import com.estetiCloud.models.service.IServicioService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/servicio")

public class ServicioController {
	
	@Autowired
    private IServicioService servicioService;

	@GetMapping(value = "/listar")
    public ResponseEntity<List<Servicio>> findAll() {
		List<Servicio>lista = servicioService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<Servicio>>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Servicio>>(lista, HttpStatus.OK); 
    }
    @PostMapping(value= "/save")
    public ResponseEntity<Servicio> create(@RequestBody Servicio servicio,BindingResult bindingResult){


        try {
        	servicioService.save(servicio);
        }catch(DataAccessException e) {
            return new ResponseEntity<Servicio>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Servicio>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/delete/{id}",  method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	Servicio ServicioActual=servicioService.findOne(id);
        	
        	servicioService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    
    
    @PutMapping(value ="/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Servicio servicio, @PathVariable Long id) {
    	Servicio ServicioActual=servicioService.findOne(id);
    	

        Map<String,Object> response =new HashMap<String, Object>();

        if(ServicioActual==null) {
            response.put("mensaje","No se pudo editar,  ID: ".concat(id.toString().concat(" no existe.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {
        	ServicioActual.setId_servicio(servicio.getId_servicio());
        	ServicioActual.setNombre(servicio.getNombre());
        	ServicioActual.setDuracion(servicio.getDuracion());
        	ServicioActual.setPrecio(servicio.getPrecio());
        	servicioService.save(ServicioActual);

        }catch(DataAccessException e) {
            response.put("mensaje","Error al actualizar");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Ha sido actualizado con éxito!");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
}
