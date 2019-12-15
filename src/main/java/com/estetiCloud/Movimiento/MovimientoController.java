package com.estetiCloud.Movimiento;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.*;

import com.estetiCloud.Profesional.Profesional;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/movimiento")

public class MovimientoController {
	
	@Autowired
    private IMovimientoService movimientoService;
		
	/*lista todos los movimientos*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@GetMapping(value = "/")
    public ResponseEntity<List<Movimiento>> findAll() {
		List<Movimiento>lista = movimientoService.findAll();
    	if (lista.isEmpty()) { 		
			return new ResponseEntity<List<Movimiento>>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		return new ResponseEntity<List<Movimiento>>(lista, HttpStatus.OK); 
    }

	/*ver un movimiento con el id*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {
		Movimiento movimiento = movimientoService.findOne(id);
    	if (movimiento==null ) {  		
			return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK); 
    }
	
	/*guardar un movimiento*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PostMapping(value= "/save")
    public ResponseEntity<Movimiento> create(@RequestBody Movimiento movimiento){
		try {
			movimientoService.save(movimiento);	
        }catch(DataAccessException e) {
            return new ResponseEntity<Movimiento>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Movimiento>(movimiento,HttpStatus.OK);
    }

	/*Elimina un movimiento*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
        	movimientoService.delete(id);
        }catch(DataAccessException e) {
            return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);

    }
	
    /*actualiza un movimiento*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PutMapping(value ="/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Movimiento movmiento, @PathVariable Long id) {
        try {
        	movimientoService.save(movmiento);
        }catch(DataAccessException e) {
            return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);
    }
	
	/*encuentra todos los movimiento por profesional*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@PostMapping(value = "/por_profesional")
    public ResponseEntity<List<Movimiento>> findbyProfesional(@RequestBody Profesional profesional) {
		List<Movimiento>lista = movimientoService.buscarPorProfesional(profesional);		
    	if (lista.isEmpty()) {		
			return new ResponseEntity<List<Movimiento>>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		return new ResponseEntity<List<Movimiento>>(lista, HttpStatus.OK); 
    }
}
