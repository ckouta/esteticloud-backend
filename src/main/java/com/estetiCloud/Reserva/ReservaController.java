package com.estetiCloud.Reserva;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.estetiCloud.Role.IRoleService;
import com.estetiCloud.Usuario.IUsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reserva")

public class ReservaController {
	
	@Autowired
    private IReservaService reservaService;

	/*lista todas las reservas*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
	@GetMapping(value = "/")
    public ResponseEntity<List<Reserva>> findAll() {
		List<Reserva>lista = reservaService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
    	if (lista.isEmpty()) {
    		response.put("mensaje","No hay reservas para mostrar");
			return new ResponseEntity<List<Reserva>>(lista,	HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<List<Reserva>>(lista, HttpStatus.OK); 
    }
	
	/*guarda reserva*/
	@Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @PostMapping(value= "/save")
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva,BindingResult bindingResult){
        try {
        	reservaService.save(reserva);
        }catch(DataAccessException e) {
            return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Reserva>(reserva,HttpStatus.ACCEPTED);
    }
	
	/*elimina resesrva*/
	@Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	reservaService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    
    /*actualiza reserva */
	@Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @PutMapping(value ="/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Reserva reserva, @PathVariable Long id) {

		try {
			reservaService.save(reserva);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
    }
}
