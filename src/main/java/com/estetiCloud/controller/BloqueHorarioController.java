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

import com.estetiCloud.models.entity.Horario_profesional;
import com.estetiCloud.models.entity.Bloque_horario;
import com.estetiCloud.models.service.IBloqueHorarioService;
import com.estetiCloud.models.service.IHorarioProfesionalService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horario")

public class BloqueHorarioController {
	
	@Autowired
    private IBloqueHorarioService bloqueService;
	@Autowired
    private IHorarioProfesionalService horarioProfesionalService;

	@GetMapping(value = "/listar")
    public ResponseEntity<List<Horario_profesional>> findAll() {
		List<Horario_profesional>lista = horarioProfesionalService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Horario_profesional>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Horario_profesional>>(lista, HttpStatus.OK); 
    }
	@GetMapping(value = "/listarbloque")
    public ResponseEntity<List<Bloque_horario>> findAllBloques() {
		List<Bloque_horario>lista = bloqueService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Bloque_horario>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Bloque_horario>>(lista, HttpStatus.OK); 
    }
    @PostMapping(value= "/save")
    public ResponseEntity<Horario_profesional> create(@RequestBody Horario_profesional cliente,BindingResult bindingResult){


        try {
        	horarioProfesionalService.save(cliente);
        }catch(DataAccessException e) {
            return new ResponseEntity<Horario_profesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Horario_profesional>(HttpStatus.ACCEPTED);
    }


    
}
