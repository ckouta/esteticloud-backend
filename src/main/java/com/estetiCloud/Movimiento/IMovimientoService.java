package com.estetiCloud.Movimiento;

import java.time.LocalDate;
import java.util.List;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Varios.IntervaloFecha;

public interface IMovimientoService {

	List<Movimiento> findAll();

	void save(Movimiento servicio);

	Movimiento findOne(Long id);

	void delete(Long id);
	
	List<Movimiento> buscarPorProfesional(Profesional profesional);
	
	List<Object> findFechaMovimiento(IntervaloFecha fecha);

}
