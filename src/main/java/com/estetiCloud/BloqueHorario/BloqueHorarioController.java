package com.estetiCloud.BloqueHorario;

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

import com.estetiCloud.HorarioProfesional.HorarioProfesional;
import com.estetiCloud.HorarioProfesional.IHorarioProfesionalService;
import com.estetiCloud.Varios.RangoHora;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horario")

public class BloqueHorarioController {
	
	@Autowired
    private IBloqueHorarioService bloqueService;
	@Autowired
    private IHorarioProfesionalService horarioProfesionalService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/listar")
    public ResponseEntity<List<HorarioProfesional>> findAll() {
		List<HorarioProfesional>lista = horarioProfesionalService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<HorarioProfesional>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<HorarioProfesional>>(lista, HttpStatus.OK); 
    }
	
	@Secured("ROLE_ADMIN")
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
	@Secured("ROLE_ADMIN")
    @PostMapping(value= "/save")
    public ResponseEntity<HorarioProfesional> create(@RequestBody HorarioProfesional cliente,BindingResult bindingResult){


        try {
        	horarioProfesionalService.save(cliente);
        }catch(DataAccessException e) {
            return new ResponseEntity<HorarioProfesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<HorarioProfesional>(HttpStatus.ACCEPTED);
    }
    
    //@PostMapping(value="/generarbloques")
    /*
     * Crear los bloques horarios para la semana en un rango de horas*/
    public ResponseEntity<Bloque_horario> createBloqueHorario(@RequestBody RangoHora rango, BindingResult bindingResult){
    	try {
    		bloqueService.generarBloques(rango);
    	 }catch(DataAccessException e) {
    		 System.out.println(e.getMessage());
    		 System.out.println(e.getStackTrace().toString());
             return new ResponseEntity<Bloque_horario>(HttpStatus.NOT_ACCEPTABLE);
         }

         return new ResponseEntity<Bloque_horario>(HttpStatus.ACCEPTED);
    }


    
}
