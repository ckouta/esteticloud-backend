package com.estetiCloud.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.Bloque_horario;

public interface IBloqueHorarioDao extends JpaRepository<Bloque_horario, Long>{

	List<Bloque_horario> findAllByDiaSemana(String dia_semana);

	List<Bloque_horario> findByDiaSemanaAndHoraInicioBetween(String diaSemana, String horaInicio, String horaFin);

	

}
