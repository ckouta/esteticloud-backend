package com.estetiCloud.models.service;

import java.util.List;


import com.estetiCloud.models.entity.Movimiento;

public interface IMovimientoService {

	List<Movimiento> findAll();

	void save(Movimiento servicio);

	Movimiento findOne(Long id);

	void delete(Long id);

}
