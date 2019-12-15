package com.estetiCloud.Movimiento;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetiCloud.Profesional.Profesional;

public interface IMovimientoDao extends JpaRepository<Movimiento, Long>{

	List<Movimiento> findByProfesional(Profesional profesional);

	

}
