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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estetiCloud.Cliente.Cliente;
import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Reserva.Reserva;
import com.estetiCloud.Varios.RangoFecha;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horarioprofesional")
public class HorarioProfesionalController {

	@Autowired
	private IHorarioProfesionalService horarioService;
	
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@PostMapping(value = "/save")
    public ResponseEntity<HorarioProfesional> save(@RequestBody RangoFecha rango,BindingResult bindingResult){

        try {
        	horarioService.saveRango(rango);
        }catch(DataAccessException e) {
            return new ResponseEntity<HorarioProfesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<HorarioProfesional>(HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
    @PostMapping(value = "/listafecha")
    public ResponseEntity<List<HorarioProfesional>> findAllFecha(@RequestBody RangoFecha rango) {
		List<HorarioProfesional>lista = horarioService.findAllFecha(rango);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<HorarioProfesional>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<HorarioProfesional>>(lista, HttpStatus.OK); 
    }
    @Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
    @PostMapping(value = "/lista")
    public ResponseEntity<List<HorarioProfesional>> findAll(@RequestBody Profesional profesional) {
		List<HorarioProfesional>lista = horarioService.findAllhoras(profesional);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<HorarioProfesional>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<HorarioProfesional>>(lista, HttpStatus.OK); 
    }
    @Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @PutMapping(value ="/reservaupdate/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Reserva reserva, @PathVariable Long id) {
    	HorarioProfesional horario=horarioService.findOne(id);
    	

        Map<String,Object> response =new HashMap<String, Object>();

        if(horario==null) {
            response.put("mensaje","No se pudo editar : ".concat(id.toString().concat(" no existe.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
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

	
}
