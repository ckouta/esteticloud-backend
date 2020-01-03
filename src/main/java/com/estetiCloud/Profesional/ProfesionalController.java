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


import com.estetiCloud.Role.IRoleService;
import com.estetiCloud.Role.Role;
import com.estetiCloud.Usuario.IUsuarioService;
import com.estetiCloud.Varios.Registro;

@CrossOrigin(origins = "*")
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

	/*lista todos los profesionales*/
	@GetMapping(value = "/")
    public ResponseEntity<List<Profesional>> findAll() {
		List<Profesional> lista;
		try {
			lista = profesionalService.findAll();
		}catch (Exception e) {
			return new ResponseEntity<List<Profesional>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Profesional>>(lista, HttpStatus.OK); 
    }
	
	/*ver un profesional*/
	@GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {
		Map<String,Object> response =new HashMap<String, Object>(); 
		Profesional profesional = profesionalService.findOne(id);
    	if (profesional==null ) {	
    		response.put("mensaje","no se encuentra el profesional");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Profesional>(profesional, HttpStatus.OK); 
    }
	
	/*busca profesional por email*/
	@GetMapping(value = "/p/{email}")
    public ResponseEntity<?> showCorreo(@PathVariable String email) {
		Map<String,Object> response =new HashMap<String, Object>(); 
		Profesional profesional = profesionalService.findOneCorreo(email);
    	if (profesional==null ) {
    		response.put("mensaje","no se encuentra el profesional");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Profesional>(profesional, HttpStatus.OK); 
    }
	
	/*crear profesional*/
	@Secured("ROLE_ADMIN")
    @PostMapping(value= "/")
    public ResponseEntity<Profesional> create(@RequestBody Profesional profesional){
		try {
			profesional.setEstado_profesional(estadoProfService.findOne((long) 1));// se le da el estado habilitado
        	profesionalService.save(profesional);
        	
        }catch(DataAccessException e) {
            return new ResponseEntity<Profesional>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Profesional>(profesional,HttpStatus.CREATED);
    }
	
	/*crea el profesional y el usuario*/
	@Secured("ROLE_ADMIN")
	@PostMapping(value= "/usuario")
    public ResponseEntity<Profesional> createUsuario(@RequestBody Registro registro,BindingResult bindingResult){
    	Role rol = roleService.findOne((long) 3);
    	Profesional profesional = registro.getProfesional();
        try {
        	profesional.setEstado_profesional(estadoProfService.findOne((long) 1));// se le da el estado habilitado
        	profesionalService.save(profesional);
        	registro.getUsuario().setPassword(passwordEncoder.encode(registro.getUsuario().getPassword()));
        	registro.getUsuario().setEnable(true);
        	usuarioService.save(registro.getUsuario());
        	 usuarioService.saveUsuario_Roles(registro.getUsuario().getId_Usuario(),rol.getId_Role() );
        }catch(DataAccessException e) {
            return new ResponseEntity<Profesional>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Profesional>(profesional,HttpStatus.CREATED);
    }
	
	/*guarda la imagen del profesional*/
	@Secured("ROLE_ADMIN")
    @PostMapping(value= "/saveimagen")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo ,@RequestParam("id") Long id ){
		try {
			Profesional profesional = profesionalService.findOne(id);
			System.out.print(profesional.toString());
        	if(!archivo.isEmpty()) {
        		String nombreArchivo = UUID.randomUUID().toString()+"_"+ archivo.getOriginalFilename().replace(" ","");
        		Path rutaArchivo = Paths.get("home/alvaro.castillo1501/uploads").resolve(nombreArchivo).toAbsolutePath();
        		Files.copy(archivo.getInputStream(), rutaArchivo);
        		String nombreFotoAnterior = profesional.getFoto();
                if(nombreFotoAnterior != null &&  nombreFotoAnterior.length() >0) {
                	Path rutaFotoAnterior = Paths.get("home/alvaro.castillo1501/uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                	File archivoFotoAnterior = rutaFotoAnterior.toFile();
                	if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                		archivoFotoAnterior.delete();
                	}
                }
        		profesional.setFoto(nombreArchivo);
        		profesionalService.save(profesional);
        	}
        }catch(DataAccessException e) {
            return new ResponseEntity<Profesional>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<Profesional>(HttpStatus.OK);
    }

	/*elimina un profesional por id*/
	@Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        try {
        	profesionalService.delete(id);
        }catch(DataAccessException e) {
            return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);
    }
    
	/*actualiza un profesional por id*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PutMapping(value ="/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Profesional profesional, @PathVariable Long id) {
    	Profesional ProfesionalActual=profesionalService.findOne(id);
        if(ProfesionalActual==null) {
           return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
        	ProfesionalActual.setNombre(profesional.getNombre());
        	ProfesionalActual.setApellido(profesional.getApellido());
        	ProfesionalActual.setDescripcion(profesional.getDescripcion());
        	ProfesionalActual.setTelefono(profesional.getTelefono());
        	ProfesionalActual.setRut(profesional.getRut());
        	profesionalService.save(ProfesionalActual);
        }catch (Exception e) {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
	}
}
