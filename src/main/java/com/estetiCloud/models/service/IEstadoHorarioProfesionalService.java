package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.EstadoHorarioProfesional;;

public interface IEstadoHorarioProfesionalService {

	List<EstadoHorarioProfesional> findAll();

	void save(EstadoHorarioProfesional estadotHorarioProfesional);

	EstadoHorarioProfesional findOne(Long id);

	void delete(Long id);

}
