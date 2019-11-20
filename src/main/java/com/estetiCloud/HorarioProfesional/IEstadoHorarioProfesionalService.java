package com.estetiCloud.HorarioProfesional;

import java.util.List;;

public interface IEstadoHorarioProfesionalService {

	List<EstadoHorarioProfesional> findAll();

	void save(EstadoHorarioProfesional estadotHorarioProfesional);

	EstadoHorarioProfesional findOne(Long id);

	void delete(Long id);

}
