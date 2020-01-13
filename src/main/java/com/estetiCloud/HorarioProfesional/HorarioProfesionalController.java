package com.estetiCloud.HorarioProfesional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Reserva.Reserva;
import com.estetiCloud.Varios.IntervaloFecha;
import com.estetiCloud.Varios.RangoFecha;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/horario")
public class HorarioProfesionalController {

	@Autowired
	private IHorarioProfesionalService horarioService;
	
	/*lista todos los horarios*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@GetMapping(value = "/")
    public ResponseEntity<List<HorarioProfesional>> findAll() {
		List<HorarioProfesional>lista = horarioService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<HorarioProfesional>>(lista,	HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<HorarioProfesional>>(lista, HttpStatus.OK); 
    }
	
	/*encuentra el top de servicios mas utilizados*/
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/topServicio")
    public ResponseEntity<List<Object>> topServicio(@RequestBody IntervaloFecha fecha) {
		List<Object>lista = horarioService.findTopServicios(fecha);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Object>>(lista,	HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<Object>>(lista, HttpStatus.OK); 
    }
	@Secured("ROLE_ESTETI")
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
		try {
			HorarioProfesional horario = horarioService.findOne(id);
			if(horario.getReserva() != null){
				horarioService.delete(id);
			}else {
				return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
        }catch(DataAccessException e) {
            return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);
    }
	/*encuentra el top de profesionales*/
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/topProfesional")
    public ResponseEntity<List<Object>> topProfesional(@RequestBody IntervaloFecha fecha) {
		List<Object>lista = horarioService.findTopProfesional(fecha);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Object>>(lista,	HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<Object>>(lista, HttpStatus.OK); 
    }
	/*encuentra el top de reservas ordenados por estados*/
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/topReservas")
    public ResponseEntity<List<Object>> topReserva(@RequestBody IntervaloFecha fecha) {
		List<Object>lista = horarioService.findTopReservas(fecha);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Object>>(lista,	HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<Object>>(lista, HttpStatus.OK); 
    }
	/*encuentro el top de servicios mas utilizados*/
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/topCliente")
    public ResponseEntity<List<Object>> topCliente(@RequestBody IntervaloFecha fecha) {
		List<Object>lista = horarioService.findTopClientes(fecha);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Object>>(lista,	HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<Object>>(lista, HttpStatus.OK); 
    }
	/*Guarda un rango de horario*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@PostMapping(value = "/save")
    public ResponseEntity<HorarioProfesional> save(@RequestBody RangoFecha rango,BindingResult bindingResult){

        try {
        	horarioService.saveRango(rango);
        }catch(DataAccessException e) {
            return new ResponseEntity<HorarioProfesional>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<HorarioProfesional>(HttpStatus.CREATED);
    }
	/*retorna los horario de una fecha y un profesional*/
    @Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
    @PostMapping(value = "/listafecha")
    public ResponseEntity<List<HorarioProfesional>> findAllFecha(@RequestBody RangoFecha rango) {
		List<HorarioProfesional>lista = horarioService.findAllFecha(rango);		
    	if (lista.isEmpty()) {    		
			return new ResponseEntity<List<HorarioProfesional>>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<HorarioProfesional>>(lista, HttpStatus.OK); 
    }
    
    /*retorna todos los horarios disponible de un profesional*/
    @Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
    @PostMapping(value = "/listaHoras")
    public ResponseEntity<List<HorarioProfesional>> findAllHoras(@RequestBody Profesional profesional) {
		List<HorarioProfesional>lista = horarioService.findAllhoras(profesional);	
    	if (lista.isEmpty()) {		
			return new ResponseEntity<List<HorarioProfesional>>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		return new ResponseEntity<List<HorarioProfesional>>(lista, HttpStatus.OK); 
    }
    
    /*actualiza un horario con la reserva asignada*/
    @Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @PutMapping(value ="/reserva/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Reserva reserva, @PathVariable Long id) {
    	HorarioProfesional horario=horarioService.findOne(id);
        Map<String,Object> response =new HashMap<String, Object>();
        if(horario==null) {
            response.put("mensaje","No se pudo editar : ".concat(id.toString().concat(" no existe.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
        	horario.setReserva(reserva);
        	horarioService.save(horario);

        }catch(DataAccessException e) {
            response.put("mensaje", e.getMessage());
            response.put("error",e.getStackTrace());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","Ha sido actualizado con éxito!");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    
    /*retorna los horarios asignados a la reserva*/
    @Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
    @PostMapping(value = "/reserva")
    public ResponseEntity<HorarioProfesional> findbyReserva(@RequestBody Reserva reserva) {
		HorarioProfesional horario = horarioService.findByReserva(reserva);	
    	if (horario==null) {    		
			return new ResponseEntity<HorarioProfesional>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<HorarioProfesional>(horario, HttpStatus.OK); 
    }
}
