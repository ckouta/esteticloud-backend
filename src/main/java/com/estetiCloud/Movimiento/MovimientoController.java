package com.estetiCloud.Movimiento;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/movimiento")

public class MovimientoController {
	
	@Autowired
    private IMovimientoService movimientoService;

	@GetMapping(value = "/listar")
    public ResponseEntity<List<Movimiento>> findAll() {
		List<Movimiento>lista = movimientoService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<Movimiento>>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<Movimiento>>(lista, HttpStatus.OK); 
    }
	
	@GetMapping(value = "/movimiento/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {
		Map<String,Object> response =new HashMap<String, Object>(); 
		Movimiento movimiento = movimientoService.findOne(id);
    	if (movimiento==null ) {
    		
    		response.put("mensaje","no se encuentra el profesional");
    		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK); 
    }
	/*probando
	@GetMapping(value = "/listaEstadoProfesional")
    public ResponseEntity<List<EstadoMovimiento>> findAllEstado() {
		List<EstadoMovimiento>lista; //= profesionalService.findAllEstado();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<EstadoMovimiento>>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<EstadoMovimiento>>(lista, HttpStatus.OK); 
    }*/
	
	@Secured("ROLE_ADMIN")
    @PostMapping(value= "/save")
    public ResponseEntity<Movimiento> create(@RequestBody Movimiento movimiento){
        
		try {
			movimientoService.save(movimiento);
        	
        }catch(DataAccessException e) {
            return new ResponseEntity<Movimiento>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Movimiento>(movimiento,HttpStatus.ACCEPTED);
    }

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{id}",  method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	movimientoService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar el profesional de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PutMapping(value ="/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Movimiento movmiento, @PathVariable Long id) {
		Movimiento movimientoactual=movimientoService.findOne(id);
    	

        Map<String,Object> response =new HashMap<String, Object>();

        if(movimientoactual==null) {
            response.put("mensaje","No se pudo editar : ".concat(id.toString().concat(" no existe.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {
        	movimientoService.save(movmiento);

        }catch(DataAccessException e) {
            response.put("mensaje", e.getMessage());
            response.put("error",e.getStackTrace());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Ha sido actualizado con éxito!");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
}
