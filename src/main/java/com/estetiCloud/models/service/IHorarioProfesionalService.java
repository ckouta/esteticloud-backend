package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.Horario_profesional;
import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.entity.RangoFecha;


public interface IHorarioProfesionalService {

	List<Horario_profesional> findAll();

	void save(Horario_profesional servicio);

	Horario_profesional findOne(Long id);

	void delete(Long id);

	void saveRango(RangoFecha rango);
	
	List<Horario_profesional> findAllFecha(RangoFecha rango);
	
	List<Horario_profesional> findAllhoras(Profesional profesional);
}
