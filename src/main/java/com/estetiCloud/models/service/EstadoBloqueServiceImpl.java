package com.estetiCloud.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.estetiCloud.models.dao.IEstadoBloqueDao;
import com.estetiCloud.models.entity.EstadoBloque;

@Service
public class EstadoBloqueServiceImpl implements IEstadoBloqueService {
	@Autowired
	private IEstadoBloqueDao estadoBloqueDao;
	
	@Transactional(readOnly=true)
	public List<EstadoBloque> findAll() {
		return estadoBloqueDao.findAll();
	}

	@Transactional
	public void save(EstadoBloque estadoBloque) {
		estadoBloqueDao.save(estadoBloque);
	}

	@Transactional(readOnly=true)
	public EstadoBloque findOne(Long id) {
		return estadoBloqueDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		estadoBloqueDao.deleteById(id);
	}
}
