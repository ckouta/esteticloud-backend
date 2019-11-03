package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.entity.Servicio;
import com.estetiCloud.models.entity.Servicio_ofrecido;

public interface IServicioOfrecidoService {

	List<Servicio_ofrecido> findAll();

	void save(Servicio_ofrecido servicioOfrecido);

	Servicio_ofrecido findOne(Long id);

	void delete(Long id);
	
	List<Servicio_ofrecido> buscarPorServicio(Servicio id);

	List<Servicio_ofrecido> buscarPorProfesional(Profesional id);
}


