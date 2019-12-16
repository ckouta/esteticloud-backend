package com.estetiCloud.Servicio;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin(origins = "http://parra.chillan.ubiobio.cl:8090/~alvaro.castillo1501/esteticloud-frontend")
@RestController
@RequestMapping("/servicio")

public class ServicioController {
	
	@Autowired
    private IServicioService servicioService;

	/*lista todos los servicios*/
	@GetMapping(value = "/")
    public ResponseEntity<List<Servicio>> findAll() {
		List<Servicio>lista;
		try {
			lista = servicioService.findAll();		
		}catch (Exception e) {
			return new ResponseEntity<List<Servicio>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Servicio>>(lista, HttpStatus.OK); 
    }
	
	/*encuentra el servicio por id*/
	@GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {
		Map<String,Object> response =new HashMap<String, Object>(); 
		Servicio servicio = servicioService.findOne(id);
    	if (servicio==null ) {    		
    		response.put("mensaje","no se encuentra el servicio");	
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK); 
    }
	
	/*crea un servicio*/
	@Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PostMapping(value= "/save")
    public ResponseEntity<Servicio> create(@RequestBody Servicio servicio ){

        try {
        	servicioService.save(servicio);
        }catch(DataAccessException e) {
            return new ResponseEntity<Servicio>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Servicio>(HttpStatus.CREATED);
    }
        
    /*guarda una imagen*/    
    @Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PostMapping(value= "/saveimagen")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,@RequestParam("id") Long id ){
    	Map<String,Object> response =new HashMap<String, Object>(); 
    	Servicio servicio = servicioService.findOne(id);
    	String nombreArchivo = null;
        try {
        	if(!archivo.isEmpty()) {
        		nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename();
        		Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
        		Files.copy(archivo.getInputStream(), rutaArchivo);
        		
        		String nombreFotoAnterior = servicio.getFoto();
        		
                if(nombreFotoAnterior != null &&  nombreFotoAnterior.length() >0) {
                	Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                	File archivoFotoAnterior = rutaFotoAnterior.toFile();
                	if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                		archivoFotoAnterior.delete();
                	}
                }
                servicio.setFoto(nombreArchivo);
                servicioService.save(servicio);
        	}
        } catch (IOException e) {
        	response.put("mensaje","no se logro agragar la imagen ");
        	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
        response.put("mensaje","correctamente la foto " + nombreArchivo);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    /*elimina un servicio*/
    @Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String,Object> response =new HashMap<String, Object>();
        try {
        	servicioService.delete(id);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al eliminar");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Eliminado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
    
    /*Actualiza un servicio*/
    @Secured({"ROLE_ADMIN","ROLE_ESTETI"})
    @PutMapping(value ="/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Servicio servicio, @PathVariable Long id) {
    	Servicio ServicioActual=servicioService.findOne(id);
        Map<String,Object> response =new HashMap<String, Object>();
        if(ServicioActual==null) {
            response.put("mensaje","No se pudo editar,  ID: ".concat(id.toString().concat(" no existe.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
        	ServicioActual.setId_servicio(servicio.getId_servicio());
        	ServicioActual.setNombre(servicio.getNombre());
        	ServicioActual.setDuracion(servicio.getDuracion());
        	ServicioActual.setPrecio(servicio.getPrecio());
        	servicioService.save(ServicioActual);
        }catch(DataAccessException e) {
            response.put("mensaje","Error al actualizar");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","Ha sido actualizado con éxito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    /*ver foto del servicio*/
    @GetMapping("uploads/img/{nombreFoto:.+}")
    public  ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
    	Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
    	Resource recurso = null;
    	try {
			recurso =  new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	if(!recurso.exists()&& !recurso.isReadable()) {
    		throw new RuntimeException("Error no se pudo cargar la imagen"+ nombreFoto);
    	}
    	HttpHeaders cabecera = new HttpHeaders();
    	cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename()+"\"");
    	return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK );
    }
}
