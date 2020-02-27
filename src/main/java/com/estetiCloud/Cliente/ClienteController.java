package com.estetiCloud.Cliente;

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

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Role.IRoleService;
import com.estetiCloud.Role.Role;
import com.estetiCloud.Usuario.IUsuarioService;
import com.estetiCloud.Usuario.Usuario;
import com.estetiCloud.Varios.Registro;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cliente")

public class ClienteController {
	
	@Autowired
    private IClienteService clienteService;
	@Autowired
    private IEstadoClienteDao estadoClienteService;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
    private IRoleService roleService;

	/*retorna la lista de clientes*/
	@Secured("ROLE_ESTETI")
	@GetMapping(value = "/")
    public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente>lista = clienteService.findAll();		
    	if (lista.isEmpty()) {
    		return new ResponseEntity<List<Cliente>>(lista,	HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK); 
    }
	
	/*encuentra los datos del cliente a travez de su correo*/
	@Secured({"ROLE_ESTETI","ROLE_CLIENT"})
	@GetMapping(value = "/{email}")
    public ResponseEntity<?> showCorreo(@PathVariable String email) {
		Cliente cliente = clienteService.findOneCorreo(email);
    	if (cliente==null ) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK); 
    }
	
	/*Guarda un cliente*/
	@Secured("ROLE_ESTETI")
    @PostMapping(value= "/save")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente,BindingResult bindingResult){
        try {
        	EstadoCliente estado = estadoClienteService.findById(1L).orElse(null);
        	cliente.setEstado_cliente(estado);
        	clienteService.save(cliente);
        }catch(DataAccessException e) {
            return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cliente>(HttpStatus.CREATED);
    }
	
	/*guarda un usuario y cliente*/
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
            return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cliente>(HttpStatus.CREATED);
    }

    /*elimina un cliente*/
    @Secured("ROLE_ESTETI")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	Cliente cliente= clienteService.findOne(id);
        	cliente.setEstado_cliente(estadoClienteService.findById(2L).orElse(null));
        	clienteService.save(cliente);
        	//clienteService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
    
    /*actualiza un cliente*/
    @Secured({"ROLE_ESTETI","ROLE_CLIENT"})
    @PutMapping(value ="/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Cliente cliente, @PathVariable Long id) {
    	Cliente Clienteactual=clienteService.findOne(id);
        if(Clienteactual==null) {
            return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
        	Clienteactual.setApellido(cliente.getApellido());
        	Clienteactual.setNombre(cliente.getNombre());
        	Clienteactual.setTelefono(cliente.getTelefono());
        	Clienteactual.setEmail(cliente.getEmail());
        	clienteService.save(Clienteactual);

        }catch(DataAccessException e) {
            return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);

    }
    /*actualiza un cliente*/
    @Secured({"ROLE_ESTETI","ROLE_CLIENT","ROLE_ADMIN"})
    @PutMapping(value ="/updateContraseña")
    public ResponseEntity<Map<String, Object>> updateContraseña(@RequestBody Registro usuario) {
    	Usuario UsuarioActual= usuarioService.findByUsername(usuario.getUsuario().getUsername());
        try {
        	if(passwordEncoder.matches(usuario.getUsuario().getPassword(), UsuarioActual.getPassword())) {
            	UsuarioActual.setPassword(passwordEncoder.encode(usuario.getNuevaContrasena()));
            	usuarioService.save(UsuarioActual);
        	}else {
        		 return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        	}


        }catch(DataAccessException e) {
            return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);

    }
}
