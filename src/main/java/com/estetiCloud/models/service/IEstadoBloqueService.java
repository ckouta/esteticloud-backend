package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.Estado_bloque;

public interface IEstadoBloqueService {

	List<Estado_bloque> findAll();

	void save(Estado_bloque profesional);

	Estado_bloque findOne(Long id);

	void delete(Long id);

}
