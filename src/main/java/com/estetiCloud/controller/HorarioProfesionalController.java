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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estetiCloud.models.entity.Cliente;
import com.estetiCloud.models.entity.Horario_profesional;
import com.estetiCloud.models.entity.RangoFecha;
import com.estetiCloud.models.service.IHorarioProfesionalService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horarioprofesional")
public class HorarioProfesionalController {

	@Autowired
	private IHorarioProfesionalService horarioService;
	
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/save")
    public ResponseEntity<Horario_profesional> save(@RequestBody RangoFecha rango,BindingResult bindingResult){

        try {
        	horarioService.saveRango(rango);
        }catch(DataAccessException e) {
            return new ResponseEntity<Horario_profesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Horario_profesional>(HttpStatus.ACCEPTED);
    }
    
    @PostMapping(value = "/lista")
    public ResponseEntity<List<Horario_profesional>> findAll(@RequestBody RangoFecha rango) {
		List<Horario_profesional>lista = horarioService.findAllhoras(rango);
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Horario_profesional>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Horario_profesional>>(lista, HttpStatus.OK); 
    }

	
}
