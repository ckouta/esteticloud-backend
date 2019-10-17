package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.Horario_profesional;


public interface IHorarioProfesionalService {

	List<Horario_profesional> findAll();

	void save(Horario_profesional servicio);

	Horario_profesional findOne(Long id);

	void delete(Long id);

}
