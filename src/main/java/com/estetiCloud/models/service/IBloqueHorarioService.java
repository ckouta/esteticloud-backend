package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.Bloque_horario;
import com.estetiCloud.models.entity.RangoHora;

public interface IBloqueHorarioService {

	List<Bloque_horario> findAll();

	void save(Bloque_horario bloque);

	Bloque_horario findOne(Long id);

	void delete(Long id);

	void generarBloques(RangoHora rango);

	List<Bloque_horario> findByDiaSemana(String dia);

	List<Bloque_horario> findByDiaSemanaAndHoraInicioBetween(String string, String horaInicio, String horaFin);

}
