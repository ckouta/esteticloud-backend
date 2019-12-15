package com.estetiCloud.ServicioOfrecido;

import java.util.List;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Servicio.Servicio;

public interface IServicioOfrecidoService {

	List<ServicioOfrecido> findAll();

	void save(ServicioOfrecido servicioOfrecido);

	ServicioOfrecido findOne(Long id);

	void delete(Long id);
	
	List<ServicioOfrecido> buscarPorServicio(Servicio id);

	List<ServicioOfrecido> buscarPorProfesional(Profesional id);
	
	List<Servicio> buscarDistintosProfesional(Profesional profesional);
}


