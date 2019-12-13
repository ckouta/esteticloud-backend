package com.estetiCloud.ProfesionalServicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Servicio.Servicio;
import com.estetiCloud.ServicioOfrecido.IServicioOfrecidoService;
import com.estetiCloud.ServicioOfrecido.ServicioOfrecido;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ps")

public class ProfesionalServicioController {
	
	@Autowired
    private IServicioOfrecidoService servicioOfrecidoService;

	
	@GetMapping(value = "/listar")
    public ResponseEntity<List<ServicioOfrecido>> findAll() {
		List<ServicioOfrecido>lista = servicioOfrecidoService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<ServicioOfrecido>>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<ServicioOfrecido>>(lista, HttpStatus.OK); 
    }
	
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PostMapping(value= "/save")
    public ResponseEntity<ServicioOfrecido> create(@RequestBody ServicioOfrecido servicioOfrecido,BindingResult bindingResult){


        try {
        	servicioOfrecidoService.save(servicioOfrecido);
        }catch(DataAccessException e) {
            return new ResponseEntity<ServicioOfrecido>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<ServicioOfrecido>(HttpStatus.ACCEPTED);
    }

	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @RequestMapping(value = "{id}",  method = RequestMethod.DELETE)
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
    
	@PostMapping(value = "/listarPS")
    public ResponseEntity<List<ServicioOfrecido>> findByServicio(@RequestBody Profesional profesional) {
		List<ServicioOfrecido> aux = servicioOfrecidoService.buscarPorProfesional(profesional);
		return new ResponseEntity<List<ServicioOfrecido>>(aux, HttpStatus.OK); 
    }
	@PostMapping(value = "/listarSP")
    public ResponseEntity<List<Profesional>> findByServicio(@RequestBody Servicio servicio) {
		List<Profesional> aux = new ArrayList<Profesional>();
    	for(ServicioOfrecido Servicio : servicioOfrecidoService.buscarPorServicio(servicio)) {
			aux.add(Servicio.getProfesional());
		}
		return new ResponseEntity<List<Profesional>>(aux, HttpStatus.OK); 
    }
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@PostMapping(value = "/listarSNO")
    public ResponseEntity<List<Servicio>> findByNotProfesional(@RequestBody Profesional profesional) {
		List<Servicio>lista = servicioOfrecidoService.buscarDistintosProfesional(profesional);
		Map<String,Object> response =new HashMap<String, Object>(); 
		if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<Servicio>>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Servicio>>(lista, HttpStatus.OK); 
    }
}
