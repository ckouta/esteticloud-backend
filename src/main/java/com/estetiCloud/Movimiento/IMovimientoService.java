package com.estetiCloud.Movimiento;

import java.util.List;

public interface IMovimientoService {

	List<Movimiento> findAll();

	void save(Movimiento servicio);

	Movimiento findOne(Long id);

	void delete(Long id);

}
