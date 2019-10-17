package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.entity.EstadoProfesional;

public interface IProfesionalService {

	List<Profesional> findAll();

	void save(Profesional profesional);

	Profesional findOne(Long id);

	void delete(Long id);
	
	List<EstadoProfesional> findAllEstado();
	
	
	
	
}
