package com.estetiCloud.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetiCloud.models.dao.IEstadoProfesionalDao;
//import com.estetiCloud.models.dao.IEstado_profesionalDao;
import com.estetiCloud.models.dao.IServicioOfrecidoDao;
import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.entity.Servicio;
import com.estetiCloud.models.entity.Servicio_ofrecido;

@Service
public class ServicioOfrecidoServiceImpl implements IServicioOfrecidoService {
	@Autowired
	private IServicioOfrecidoDao servicioOfrecidoDao;
	
	
	
	@Transactional(readOnly=true)
	public List<Servicio_ofrecido> findAll() {
		return servicioOfrecidoDao.findAll();
	}

	@Transactional
	public void save(Servicio_ofrecido servicioOfrecido) {
		servicioOfrecidoDao.save(servicioOfrecido);
	}

	@Transactional(readOnly=true)
	public Servicio_ofrecido findOne(Long id) {
		return servicioOfrecidoDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		servicioOfrecidoDao.deleteById(id);
	}

	@Transactional(readOnly=true)
	public List<Servicio_ofrecido> buscarPorServicio(Servicio servicio) {
		// TODO Auto-generated method stub
		return servicioOfrecidoDao.findByServicio(servicio);
	}

	@Transactional(readOnly=true)
	public List<Servicio_ofrecido> buscarPorProfesional(Profesional profesional) {
		// TODO Auto-generated method stub
		return servicioOfrecidoDao.findByProfesional(profesional);
	}

}
