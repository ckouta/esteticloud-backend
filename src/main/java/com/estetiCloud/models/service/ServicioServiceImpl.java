package com.estetiCloud.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.estetiCloud.models.dao.IServicioDao;
import com.estetiCloud.models.entity.Servicio;

@Service
public class ServicioServiceImpl implements IServicioService {
	@Autowired
	private IServicioDao servicioDao;
	
	@Transactional(readOnly=true)
	public List<Servicio> findAll() {
		return servicioDao.findAll();
	}

	@Transactional
	public void save(Servicio servicio) {
		servicioDao.save(servicio);
	}

	@Transactional(readOnly=true)
	public Servicio findOne(Long id) {
		return servicioDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		servicioDao.deleteById(id);
	}
}
