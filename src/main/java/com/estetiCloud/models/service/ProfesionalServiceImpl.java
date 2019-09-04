package com.estetiCloud.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetiCloud.models.dao.IProfesionalDao;
import com.estetiCloud.models.entity.Profesional;

@Service
public class ProfesionalServiceImpl implements IProfesionalService {
	@Autowired
	private IProfesionalDao profesionalDao;
	
	@Override
	public List<Profesional> findAll() {
		return profesionalDao.findAll();
	}

	@Override
	public void save(Profesional profesional) {
		profesionalDao.save(profesional);
	}

	@Override
	public Profesional findOne(Long id) {
		return profesionalDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		profesionalDao.deleteById(id);
	}
}
