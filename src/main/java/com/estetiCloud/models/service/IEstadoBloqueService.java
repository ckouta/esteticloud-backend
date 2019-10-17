package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.EstadoBloque;

public interface IEstadoBloqueService {

	List<EstadoBloque> findAll();

	void save(EstadoBloque profesional);

	EstadoBloque findOne(Long id);

	void delete(Long id);

}
