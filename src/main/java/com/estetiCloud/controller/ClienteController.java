package com.estetiCloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.estetiCloud.models.entity.Cliente;
import com.estetiCloud.models.entity.Registro;
import com.estetiCloud.models.entity.Role;
import com.estetiCloud.models.service.IClienteService;
import com.estetiCloud.models.service.IRoleService;
import com.estetiCloud.models.service.IUsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cliente")

public class ClienteController {
	
	@Autowired
    private IClienteService clienteService;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
    private IRoleService roleService;

	@GetMapping(value = "/listar")
    public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente>lista = clienteService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","No hay clientes para mostrar");
    		
			return new ResponseEntity<List<Cliente>>(lista,	HttpStatus.NOT_FOUND);
			
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
    @PostMapping(value= "/usuario")
    public ResponseEntity<Cliente> createUsuario(@RequestBody Registro registro,BindingResult bindingResult){
    	Role rol = roleService.findOne((long) 2);

        try {
        	
        	clienteService.save(registro.getCliente());
        	registro.getUsuario().setPassword(passwordEncoder.encode(registro.getUsuario().getPassword()));
        	registro.getUsuario().setEnable(true);
        	usuarioService.save(registro.getUsuario());
        	 usuarioService.saveUsuario_Roles(registro.getUsuario().getId_Usuario(),rol.getId_Role() );
        }catch(DataAccessException e) {
            return new ResponseEntity<Cliente>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Cliente>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/delete/{id}",  method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	clienteService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
    
    
    @PutMapping(value ="/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Cliente cliente, @PathVariable Long id) {
    	Cliente Clienteactual=clienteService.findOne(id);
    	

        Map<String,Object> response =new HashMap<String, Object>();

        if(Clienteactual==null) {
            response.put("mensaje","No se pudo editar, el funcionario con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {

        	Clienteactual.setNombre(cliente.getNombre());
        	Clienteactual.setApellido(cliente.getApellido());
        	Clienteactual.setTelefono(cliente.getTelefono());
        	Clienteactual.setEmail(cliente.getEmail());
        	clienteService.save(Clienteactual);

        }catch(DataAccessException e) {
            response.put("mensaje", e.getMessage());
            response.put("error",e.getStackTrace());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El Cliente ha sido actualizado con éxito!");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
}
