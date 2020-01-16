package com.estetiCloud.BloqueHorario;

import java.util.List;

import com.estetiCloud.Varios.RangoHora;

public interface IBloqueHorarioService {

	List<BloqueHorario> findAll();

	void save(BloqueHorario bloque);

	BloqueHorario findOne(Long id);

	void delete(Long id);

	void generarBloques(RangoHora rango);

	List<BloqueHorario> findByDiaSemana(String dia);

	List<BloqueHorario> findByDiaSemanaAndHoraInicioBetween(String string, String horaInicio, String horaFin);

}
