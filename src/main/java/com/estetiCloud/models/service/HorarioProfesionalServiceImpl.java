package com.estetiCloud.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.estetiCloud.models.dao.IHorarioProfesionalDao;
import com.estetiCloud.models.entity.Horario_profesional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class HorarioProfesionalServiceImpl implements IHorarioProfesionalService {
	@Autowired
	private IHorarioProfesionalDao horarioProfesionalDao;
	
	@Transactional(readOnly=true)
	public List<Horario_profesional> findAll() {
		return horarioProfesionalDao.findAll();
	}

	@Transactional
	public void save(Horario_profesional horarioProfesional) {
		horarioProfesionalDao.save(horarioProfesional);
	}

	@Transactional(readOnly=true)
	public Horario_profesional findOne(Long id) {
		return horarioProfesionalDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		horarioProfesionalDao.deleteById(id);
	}
}
