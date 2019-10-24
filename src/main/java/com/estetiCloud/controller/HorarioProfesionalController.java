package com.estetiCloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estetiCloud.models.entity.Cliente;
import com.estetiCloud.models.entity.Horario_profesional;
import com.estetiCloud.models.entity.RangoFecha;
import com.estetiCloud.models.service.IHorarioProfesionalService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horarioprofesional")
public class HorarioProfesionalController {

	@Autowired
	private IHorarioProfesionalService horarioService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<Horario_profesional> save(@RequestBody RangoFecha rango,BindingResult bindingResult){

        try {
        	horarioService.saveRango(rango);
        }catch(DataAccessException e) {
            return new ResponseEntity<Horario_profesional>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Horario_profesional>(HttpStatus.ACCEPTED);
    }

	
}
