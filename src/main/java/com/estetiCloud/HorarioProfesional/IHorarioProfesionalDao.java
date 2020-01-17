package com.estetiCloud.HorarioProfesional;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Reserva.Reserva;
import com.estetiCloud.BloqueHorario.BloqueHorario;



public interface IHorarioProfesionalDao extends JpaRepository<HorarioProfesional, Long>{

	List<HorarioProfesional> findByFechaAndProfesionalOrderByFecha(LocalDate fecha, Profesional profesional);

	List<HorarioProfesional> findByProfesional(Profesional profesional);

	List<HorarioProfesional> findByReserva(Reserva reserva);
	
	HorarioProfesional findByFechaAndBloqueHorarioAndProfesional(LocalDate fecha,BloqueHorario bloque_horario, Profesional profesional);
	
	@Query("SELECT  h.reserva.servicio, COUNT( Distinct h.reserva) from HorarioProfesional h where (h.fecha between ?1 and ?2) group by (h.reserva.servicio.id_servicio)")
	List<Object> findTopServicios( LocalDate fechaInicio , LocalDate fechaFin);
	
	@Query("SELECT Distinct h.reserva, h.fecha, h.profesional from HorarioProfesional h where (h.fecha between ?1 and ?2)")
	List<Object> findTopReservas( LocalDate fechaInicio , LocalDate fechaFin);
	
	@Query("SELECT  h.reserva.cliente, COUNT( Distinct h.reserva)  from HorarioProfesional h where (h.fecha between ?1 and ?2) group by (h.reserva.cliente.id_cliente)")
	List<Object> findTopClientes( LocalDate fechaInicio , LocalDate fechaFin);
	
	@Query("SELECT  h.profesional, COUNT( Distinct h.reserva)from HorarioProfesional h where (h.fecha between ?1 and ?2) group by (h.reserva)")
	List<Object> findTopProfesional( LocalDate fechaInicio , LocalDate fechaFin);
}
