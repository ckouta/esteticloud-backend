package com.estetiCloud.models.dao;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.Horario_profesional;
import com.estetiCloud.models.entity.Profesional;



public interface IHorarioProfesionalDao extends JpaRepository<Horario_profesional, Long>{

	List<Horario_profesional> findByFechaAndProfesional(LocalDate fecha, Profesional profesional);

	List<Horario_profesional> findByProfesional(Profesional profesional);

}
