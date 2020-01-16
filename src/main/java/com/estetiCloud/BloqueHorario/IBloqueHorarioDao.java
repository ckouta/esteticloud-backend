package com.estetiCloud.BloqueHorario;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBloqueHorarioDao extends JpaRepository<BloqueHorario, Long>{

	List<BloqueHorario> findAllByDiaSemana(String dia_semana);

	List<BloqueHorario> findByDiaSemanaAndHoraInicioBetween(String diaSemana, String horaInicio, String horaFin);

	

}
