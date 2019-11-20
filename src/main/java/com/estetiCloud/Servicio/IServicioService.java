package com.estetiCloud.Servicio;

import java.util.List;

public interface IServicioService {

	List<Servicio> findAll();

	void save(Servicio servicio);

	Servicio findOne(Long id);

	void delete(Long id);

}
