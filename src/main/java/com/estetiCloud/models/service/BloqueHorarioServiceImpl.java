package com.estetiCloud.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.estetiCloud.models.dao.IBloqueHorarioDao;
import com.estetiCloud.models.entity.Bloque_horario;

@Service
public class BloqueHorarioServiceImpl implements IBloqueHorarioService {
	@Autowired
	private IBloqueHorarioDao bloqueDao;
	
	@Transactional(readOnly=true)
	public List<Bloque_horario> findAll() {
		return bloqueDao.findAll();
	}

	@Transactional
	public void save(Bloque_horario cliente) {
		bloqueDao.save(cliente);
	}

	@Transactional(readOnly=true)
	public Bloque_horario findOne(Long id) {
		return bloqueDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		bloqueDao.deleteById(id);
	}
}
