package com.estetiCloud.controller;

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

import com.estetiCloud.models.entity.Cliente;
import com.estetiCloud.models.entity.Horario_profesional;
import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.entity.RangoFecha;
import com.estetiCloud.models.entity.Reserva;
import com.estetiCloud.models.service.IHorarioProfesionalService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horarioprofesional")
public class HorarioProfesionalController {

	@Autowired
	private IHorarioProfesionalService horarioService;
	
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@PostMapping(value = "/save")
    public ResponseEntity<Horario_profesional> save(@RequestBody RangoFecha rango,BindingResult bindingResult){

        try {
        	horarioService.saveRango(rango);
        }catch(DataAccessException e) {
            return new ResponseEntity<Horario_profesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Horario_profesional>(HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
    @PostMapping(value = "/listafecha")
    public ResponseEntity<List<Horario_profesional>> findAllFecha(@RequestBody RangoFecha rango) {
		List<Horario_profesional>lista = horarioService.findAllFecha(rango);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Horario_profesional>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Horario_profesional>>(lista, HttpStatus.OK); 
    }
    @Secured({"ROLE_ADMIN","ROLE_ESTETI","ROLE_CLIENT"})
    @PostMapping(value = "/lista")
    public ResponseEntity<List<Horario_profesional>> findAll(@RequestBody Profesional profesional) {
		List<Horario_profesional>lista = horarioService.findAllhoras(profesional);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Horario_profesional>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Horario_profesional>>(lista, HttpStatus.OK); 
    }
    @Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @PutMapping(value ="/reservaupdate/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Reserva reserva, @PathVariable Long id) {
    	Horario_profesional horario=horarioService.findOne(id);
    	

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
