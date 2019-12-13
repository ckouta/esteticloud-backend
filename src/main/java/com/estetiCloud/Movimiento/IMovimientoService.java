package com.estetiCloud.Movimiento;

import java.util.List;

import com.estetiCloud.Profesional.Profesional;

public interface IMovimientoService {

	List<Movimiento> findAll();

	void save(Movimiento servicio);

	Movimiento findOne(Long id);

	void delete(Long id);
	
	List<Movimiento> buscarPorProfesional(Profesional profesional);

}
