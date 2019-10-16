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

import com.estetiCloud.models.entity.Cliente;
import com.estetiCloud.models.service.IClienteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cliente")

public class ClienteController {
	
	@Autowired
    private IClienteService clienteService;

	@GetMapping(value = "/listaCliente")
    public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente>lista = clienteService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<Cliente>>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK); 
    }
    @PostMapping(value= "/save")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente,BindingResult bindingResult){


        try {
        	clienteService.save(cliente);
        }catch(DataAccessException e) {
            return new ResponseEntity<Cliente>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Cliente>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/DeleteProfesional/{id}",  method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	clienteService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar el profesional de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El profesional fue eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    
    
    @PutMapping(value ="/updateProfesional/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Cliente cliente, @PathVariable Long id) {
    	Cliente Clienteactual=clienteService.findOne(id);
    	

        Map<String,Object> response =new HashMap<String, Object>();

        if(Clienteactual==null) {
            response.put("mensaje","No se pudo editar, el funcionario con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {
        	Clienteactual.setId(cliente.getId());
        	Clienteactual.setNombre(cliente.getNombre());
        	Clienteactual.setApellido(cliente.getApellido());
        	Clienteactual.setTelefono(cliente.getTelefono());
        	Clienteactual.setEmail(cliente.getEmail());
        	clienteService.save(Clienteactual);

        }catch(DataAccessException e) {
            response.put("mensaje","Error al actualizar el cliente en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El Cliente ha sido actualizado con éxito!");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
}
