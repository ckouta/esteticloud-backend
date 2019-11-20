package com.estetiCloud.Profesional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.estetiCloud.Cliente.Cliente;
import com.estetiCloud.Role.IRoleService;
import com.estetiCloud.Role.Role;
import com.estetiCloud.Servicio.Servicio;
import com.estetiCloud.Usuario.IUsuarioService;
import com.estetiCloud.Varios.Registro;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/profesional")

public class ProfesionalController {
	
	@Autowired
    private IProfesionalService profesionalService;

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
    private IRoleService roleService;

	
	@Autowired
	private IEstadoProfesionalService estadoProfService;


	@GetMapping(value = "/listar")
    public ResponseEntity<List<Profesional>> findAll() {
		List<Profesional>lista = profesionalService.findAll();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<Profesional>>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<Profesional>>(lista, HttpStatus.OK); 
    }
	
	
	@GetMapping(value = "/profesional/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {
		Map<String,Object> response =new HashMap<String, Object>(); 
		Profesional profesional = profesionalService.findOne(id);
    	if (profesional==null ) {
    		
    		response.put("mensaje","no se encuentra el profesional");
    		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Profesional>(profesional, HttpStatus.OK); 
    }
	
	
	@GetMapping(value = "/{email}")
    public ResponseEntity<?> showCorreo(@PathVariable String email) {
		Map<String,Object> response =new HashMap<String, Object>(); 
		Profesional profesional = profesionalService.findOneCorreo(email);
    	if (profesional==null ) {
    		
    		response.put("mensaje","no se encuentra el profesional");
    		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Profesional>(profesional, HttpStatus.OK); 
    }
	/*probando*/
	@GetMapping(value = "/listaEstadoProfesional")
    public ResponseEntity<List<EstadoProfesional>> findAllEstado() {
		List<EstadoProfesional>lista = profesionalService.findAllEstado();
		Map<String,Object> response =new HashMap<String, Object>(); 
		
    	if (lista.isEmpty()) {
    		
    		response.put("mensaje","no hay lista ");
    		
			return new ResponseEntity<List<EstadoProfesional>>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<List<EstadoProfesional>>(lista, HttpStatus.OK); 
    }
	
	@Secured("ROLE_ADMIN")
    @PostMapping(value= "/save")
    public ResponseEntity<Profesional> create(@RequestBody Profesional profesional){
        
		try {
			profesional.setEstado_profesional(estadoProfService.findOne((long) 1));// se le da el estado habilitado
        	profesionalService.save(profesional);
        	
        }catch(DataAccessException e) {
            return new ResponseEntity<Profesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Profesional>(profesional,HttpStatus.ACCEPTED);
    }
	
	
	@Secured("ROLE_ADMIN")
	@PostMapping(value= "/usuario")
    public ResponseEntity<Profesional> createUsuario(@RequestBody Registro registro,BindingResult bindingResult){
    	Role rol = roleService.findOne((long) 3);

        try {
        	
        	profesionalService.save(registro.getProfesional());
        	registro.getUsuario().setPassword(passwordEncoder.encode(registro.getUsuario().getPassword()));
        	registro.getUsuario().setEnable(true);
        	usuarioService.save(registro.getUsuario());
        	 usuarioService.saveUsuario_Roles(registro.getUsuario().getId_Usuario(),rol.getId_Role() );
        }catch(DataAccessException e) {
            return new ResponseEntity<Profesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Profesional>(registro.getProfesional(),HttpStatus.ACCEPTED);
    }
	@Secured("ROLE_ADMIN")
    @PostMapping(value= "/saveimagen")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo ,@RequestParam("id") Long id ){
        
		try {
			Profesional profesional = profesionalService.findOne(id);
        	if(!archivo.isEmpty()) {
        		String nombreArchivo = UUID.randomUUID().toString()+"_"+ archivo.getOriginalFilename().replace(" ","");
        		Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
        		Files.copy(archivo.getInputStream(), rutaArchivo);
        		String nombreFotoAnterior = profesional.getFoto();
        		
                if(nombreFotoAnterior != null &&  nombreFotoAnterior.length() >0) {
                	Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                	File archivoFotoAnterior = rutaFotoAnterior.toFile();
                	if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                		archivoFotoAnterior.delete();
                	}
                }
        		profesional.setFoto(nombreArchivo);
        		profesionalService.save(profesional);
        	}
        	
        	
        }catch(DataAccessException e) {
            return new ResponseEntity<Profesional>(HttpStatus.NOT_ACCEPTABLE);
        } catch (IOException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<Profesional>(HttpStatus.ACCEPTED);
    }

	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}",  method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	profesionalService.delete(id);
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
    public ResponseEntity<Map<String, Object>> update(@RequestBody Profesional profesional, @PathVariable Long id) {
    	Profesional ProfesionalActual=profesionalService.findOne(id);
    	

        Map<String,Object> response =new HashMap<String, Object>();

        if(ProfesionalActual==null) {
            response.put("mensaje","No se pudo editar : ".concat(id.toString().concat(" no existe.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try {
        	ProfesionalActual.setId_profesional(profesional.getId_profesional());
        	ProfesionalActual.setNombre(profesional.getNombre());
        	ProfesionalActual.setApellido(profesional.getApellido());
        	ProfesionalActual.setTelefono(profesional.getTelefono());
        	ProfesionalActual.setEmail(profesional.getEmail());
        	profesionalService.save(ProfesionalActual);

        }catch(DataAccessException e) {
            response.put("mensaje", e.getMessage());
            response.put("error",e.getStackTrace());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Ha sido actualizado con éxito!");

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

 
	}
}
