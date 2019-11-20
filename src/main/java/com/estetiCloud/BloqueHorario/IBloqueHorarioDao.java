package com.estetiCloud.BloqueHorario;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBloqueHorarioDao extends JpaRepository<Bloque_horario, Long>{

	List<Bloque_horario> findAllByDiaSemana(String dia_semana);

	List<Bloque_horario> findByDiaSemanaAndHoraInicioBetween(String diaSemana, String horaInicio, String horaFin);

	

}
