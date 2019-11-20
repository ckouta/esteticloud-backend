package com.estetiCloud.Profesional;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionalServiceImpl implements IProfesionalService {
	@Autowired
	private IProfesionalDao profesionalDao;
	@Autowired
	private IEstadoProfesionalDao estadoprofesionalDao;
	
	
	
	@Transactional(readOnly=true)
	public List<Profesional> findAll() {
		return profesionalDao.findAll();
	}

	@Transactional
	public void save(Profesional profesional) {
		profesionalDao.save(profesional);
	}

	@Transactional(readOnly=true)
	public Profesional findOne(Long id) {
		return profesionalDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		profesionalDao.deleteById(id);
	}

	@Transactional(readOnly=true)
	public List<EstadoProfesional> findAllEstado() {
		// TODO Auto-generated method stub
		return estadoprofesionalDao.findAll();
	}
	@Transactional(readOnly=true)
	public Profesional findOneCorreo(String email) {
		return  profesionalDao.findByEmail(email);
	}

}
