package com.estetiCloud.HorarioProfesional;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Reserva.Reserva;



public interface IHorarioProfesionalDao extends JpaRepository<HorarioProfesional, Long>{

	List<HorarioProfesional> findByFechaAndProfesional(LocalDate fecha, Profesional profesional);

	List<HorarioProfesional> findByProfesional(Profesional profesional);

	List<HorarioProfesional> findByReserva(Reserva reserva);

}
