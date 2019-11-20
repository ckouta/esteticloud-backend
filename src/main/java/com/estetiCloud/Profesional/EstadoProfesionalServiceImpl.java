package com.estetiCloud.Profesional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoProfesionalServiceImpl implements IEstadoProfesionalService{

	@Autowired
	private IEstadoProfesionalDao estadoProfesionalDao;
	
	@Transactional
	public void delete(Long id) {
		estadoProfesionalDao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public List<EstadoProfesional> findAll() {
		return estadoProfesionalDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public EstadoProfesional findOne(Long id) {
		return estadoProfesionalDao.findById(id).orElse(null);
	}
	
	@Transactional
	public void save(EstadoProfesional estadoProfesional) {
		estadoProfesionalDao.save(estadoProfesional);
		
	}

}
