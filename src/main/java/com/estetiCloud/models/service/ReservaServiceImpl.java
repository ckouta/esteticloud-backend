package com.estetiCloud.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.estetiCloud.models.dao.IReservaDao;
import com.estetiCloud.models.entity.Reserva;

@Service
public class ReservaServiceImpl implements IReservaService {
	@Autowired
	private IReservaDao reservaDao;
	
	@Transactional(readOnly=true)
	public List<Reserva> findAll() {
		return reservaDao.findAll();
	}

	@Transactional
	public void save(Reserva reserva) {
		reservaDao.save(reserva);
	}

	@Transactional(readOnly=true)
	public Reserva findOne(Long id) {
		return reservaDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		reservaDao.deleteById(id);
	}
}
