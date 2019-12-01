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

	@Secured({"ROLE_CLIENT","ROLE_ESTETI"})
	@GetMapping(value = "/listar")
    public ResponseEntity<List<Reserva>> findAll() {
		List<Reserva>lista = reservaService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Reserva>>(lista,	HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Reserva>>(lista, HttpStatus.OK); 
    }
	@Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @PostMapping(value= "/save")
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva,BindingResult bindingResult){


        try {
        	reservaService.save(reserva);
        }catch(DataAccessException e) {
            return new ResponseEntity<Reserva>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Reserva>(reserva,HttpStatus.CREATED);
    }
	@Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @RequestMapping(value = "/delete/{id}",  method = RequestMethod.DELETE)
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
    
    /*cambiar */
	@Secured({"ROLE_CLIENT","ROLE_ESTETI"})
    @PutMapping(value ="/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Reserva reserva, @PathVariable Long id) {
    	Reserva Clienteactual=reservaService.findOne(id);
    	

        Map<String,Object> response =new HashMap<String, Object>();

        if(Clienteactual==null) {
            response.put("mensaje","No se pudo editar, el funcionario con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {

        	

        }catch(DataAccessException e) {
            response.put("mensaje", e.getMessage());
            response.put("error",e.getStackTrace());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El Cliente ha sido actualizado con éxito!");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
}
