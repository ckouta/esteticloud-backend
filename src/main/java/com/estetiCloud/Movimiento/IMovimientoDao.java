package com.estetiCloud.Movimiento;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estetiCloud.Profesional.Profesional;

public interface IMovimientoDao extends JpaRepository<Movimiento, Long>{

	List<Movimiento> findByProfesional(Profesional profesional);

	@Query("SELECT  m  from Movimiento m where (m.fecha between ?1 and ?2)")
	List<Object> findFechaMovimientos( LocalDate fechaInicio , LocalDate fechaFin);

}
