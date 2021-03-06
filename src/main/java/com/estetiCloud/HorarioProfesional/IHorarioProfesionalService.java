package com.estetiCloud.HorarioProfesional;

import java.util.List;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Reserva.Reserva;
import com.estetiCloud.Varios.IntervaloFecha;
import com.estetiCloud.Varios.RangoFecha;


public interface IHorarioProfesionalService {

	List<HorarioProfesional> findAll();

	void save(HorarioProfesional servicio);

	HorarioProfesional findOne(Long id);

	void delete(Long id);

	void saveRango(RangoFecha rango);
	
	List<HorarioProfesional> findAllFecha(RangoFecha rango);
	
	List<HorarioProfesional> findAllhoras(Profesional profesional);
	
	HorarioProfesional findByReserva(Reserva reserva);
	
	List<Object> findTopServicios (IntervaloFecha fecha);
	
	List<Object> findTopReservas (IntervaloFecha fecha);
	
	List<Object> findTopClientes (IntervaloFecha fecha);
	
	List<Object> findTopProfesional (IntervaloFecha fecha);
}
