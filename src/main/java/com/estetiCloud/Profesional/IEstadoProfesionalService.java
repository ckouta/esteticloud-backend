package com.estetiCloud.Profesional;

import java.util.List;

public interface IEstadoProfesionalService {

	List<EstadoProfesional> findAll();

	void save(EstadoProfesional estadoProfesional);

	EstadoProfesional findOne(Long id);

	void delete(Long id);

}
