package com.estetiCloud.ServicioOfrecido;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Servicio.IServicioDao;
import com.estetiCloud.Servicio.Servicio;

@Service
public class ServicioOfrecidoServiceImpl implements IServicioOfrecidoService {
	@Autowired
	private IServicioOfrecidoDao servicioOfrecidoDao;
	@Autowired
	private IServicioDao servicio;
	
	
	@Transactional(readOnly=true)
	public List<ServicioOfrecido> findAll() {
		return servicioOfrecidoDao.findAll();
	}

	@Transactional
	public void save(ServicioOfrecido servicioOfrecido) {
		servicioOfrecidoDao.save(servicioOfrecido);
	}

	@Transactional(readOnly=true)
	public ServicioOfrecido findOne(Long id) {
		return servicioOfrecidoDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		servicioOfrecidoDao.deleteById(id);
	}

	@Transactional(readOnly=true)
	public List<ServicioOfrecido> buscarPorServicio(Servicio servicio) {
		// TODO Auto-generated method stub
		return servicioOfrecidoDao.findByServicio(servicio);
	}

	@Transactional(readOnly=true)
	public List<ServicioOfrecido> buscarPorProfesional(Profesional profesional) {
		// TODO Auto-generated method stub
		return servicioOfrecidoDao.findByProfesional(profesional);
	}
	public List<Servicio> buscarDistintosProfesional(Profesional profesional) {
		// TODO Auto-generated method stub
		List<Servicio> servicios = servicio.findAll();
    		for(ServicioOfrecido servicioOfrecido : servicioOfrecidoDao.findByProfesional(profesional)) {
    			servicios.remove(servicioOfrecido.getServicio());
    		}
		
		return servicios;
	}

}
