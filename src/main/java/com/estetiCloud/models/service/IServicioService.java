package com.estetiCloud.models.service;

import java.util.List;


import com.estetiCloud.models.entity.Servicio;

public interface IServicioService {

	List<Servicio> findAll();

	void save(Servicio servicio);

	Servicio findOne(Long id);

	void delete(Long id);

}
