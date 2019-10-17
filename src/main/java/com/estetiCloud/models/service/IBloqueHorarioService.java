package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.Bloque_horario;

public interface IBloqueHorarioService {

	List<Bloque_horario> findAll();

	void save(Bloque_horario profesional);

	Bloque_horario findOne(Long id);

	void delete(Long id);

}
