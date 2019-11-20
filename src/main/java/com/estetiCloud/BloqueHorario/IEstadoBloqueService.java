package com.estetiCloud.BloqueHorario;

import java.util.List;

public interface IEstadoBloqueService {

	List<EstadoBloque> findAll();

	void save(EstadoBloque profesional);

	EstadoBloque findOne(Long id);

	void delete(Long id);

}
