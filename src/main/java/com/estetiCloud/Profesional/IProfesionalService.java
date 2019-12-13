package com.estetiCloud.Profesional;

import java.util.List;

public interface IProfesionalService {

	List<Profesional> findAll();

	void save(Profesional profesional);

	Profesional findOne(Long id);

	void delete(Long id);
	
	List<EstadoProfesional> findAllEstado();
	
	Profesional findOneCorreo(String correo);
	
	Profesional findOneRut(String rut);
}
