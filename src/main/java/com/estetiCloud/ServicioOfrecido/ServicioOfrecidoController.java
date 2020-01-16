package com.estetiCloud.ServicioOfrecido;

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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/so")

public class ServicioOfrecidoController {
	
	@Autowired
    private IServicioOfrecidoService servicioOfrecidoService;
	@Autowired
    private IEstadoServicioOfrecidoDao estadoServicioOfrecido;

	/*listar servicios ofrecidos*/
	@GetMapping(value = "/")
    public ResponseEntity<List<ServicioOfrecido>> findAll() {
		List<ServicioOfrecido>lista = servicioOfrecidoService.findAll();
    	if (lista.isEmpty()) {
			return new ResponseEntity<List<ServicioOfrecido>>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return new ResponseEntity<List<ServicioOfrecido>>(lista, HttpStatus.OK); 
    }
	
	/*guardar servicio ofrecido*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PostMapping(value= "/save")
    public ResponseEntity<ServicioOfrecido> create(@RequestBody ServicioOfrecido servicioOfrecido,BindingResult bindingResult){
        try {
        	servicioOfrecido.setEstado_servicioOfrecido(estadoServicioOfrecido.findById(1L).orElse(null));
        	servicioOfrecidoService.save(servicioOfrecido);
        }catch(DataAccessException e) {
            return new ResponseEntity<ServicioOfrecido>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ServicioOfrecido>(HttpStatus.OK);
    }

	/* eliminar servicio ofrecido*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @DeleteMapping(value = "/{id}")
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
    
	/*listar servicios ofrecido por un profesional*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
	@PostMapping(value = "/por_profesional")
    public ResponseEntity<List<ServicioOfrecido>> findByServicio(@RequestBody Profesional profesional) {
		List<ServicioOfrecido> aux = servicioOfrecidoService.buscarPorProfesional(profesional);
		
		return new ResponseEntity<List<ServicioOfrecido>>(aux, HttpStatus.OK); 
    }
	
	/*lista los profesionales que realizan un servicio*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
	@PostMapping(value = "/profesional")
    public ResponseEntity<List<Profesional>> findByServicio(@RequestBody Servicio servicio) {
		List<Profesional> aux = new ArrayList<Profesional>();
    	for(ServicioOfrecido Servicio : servicioOfrecidoService.buscarPorServicio(servicio)) {
			aux.add(Servicio.getProfesional());
		}
		return new ResponseEntity<List<Profesional>>(aux, HttpStatus.OK); 
    }
	
	/*listar los servicios que no tienen un profesional asignado*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@PostMapping(value = "/sin_profesional")
    public ResponseEntity<List<Servicio>> findByNotProfesional(@RequestBody Profesional profesional) {
		List<Servicio>lista = servicioOfrecidoService.buscarDistintosProfesional(profesional);
		return new ResponseEntity<List<Servicio>>(lista, HttpStatus.OK); 
    }
}
